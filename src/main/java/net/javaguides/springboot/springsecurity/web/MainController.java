package net.javaguides.springboot.springsecurity.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.javaguides.springboot.springsecurity.model.Departamento;
import net.javaguides.springboot.springsecurity.model.Linea;
import net.javaguides.springboot.springsecurity.model.Profesor;
import net.javaguides.springboot.springsecurity.model.Tesis;
import net.javaguides.springboot.springsecurity.repository.AlumnoRepository;
import net.javaguides.springboot.springsecurity.repository.Conferencias_dictadas_Repository;
import net.javaguides.springboot.springsecurity.repository.CursosRepository;
import net.javaguides.springboot.springsecurity.repository.DepartamentoRepository;
import net.javaguides.springboot.springsecurity.repository.LineaRepository;
import net.javaguides.springboot.springsecurity.repository.ProfesorRepository;
import net.javaguides.springboot.springsecurity.repository.PublicacionesRepository;
import net.javaguides.springboot.springsecurity.repository.TesisRepository;
import net.javaguides.springboot.springsecurity.repository.lineasInvestigacionCuerposRepository;

@Controller
public class MainController {

	private Long ver = new Date().getTime();

	@PersistenceContext
	private EntityManager em;

	@Autowired
	AlumnoRepository alumnoRepository;

	@Autowired
	LineaRepository lineaRepository;

	@Autowired
	ProfesorRepository profesorRepository;

	@Autowired
	PublicacionesRepository publicacionesRepository;

	@Autowired
	CursosRepository cursosRepository;

	@Autowired
	Conferencias_dictadas_Repository conferenciasRepository;

	@Autowired
	TesisRepository tesisRepository;

	@Autowired
	private DepartamentoRepository departamentoRepository;

	@Autowired
	private lineasInvestigacionCuerposRepository cuerposRepository;

	@SuppressWarnings({ "null", "unchecked" })
	@GetMapping("/Estadisticas")
	public String root(@RequestParam(defaultValue = "0") String fechainicio,
			@RequestParam(defaultValue = "0") String fechafinal,
			@RequestParam(defaultValue = "0") String select_linea_investiga,
			@RequestParam(defaultValue = "0") String select_profesor,
			@RequestParam(defaultValue = "0") String select_departamento, Model model) throws ParseException {
		Long select_profesor1 = null;
		if (select_profesor.equals("0")) {
		} else {
			List<Profesor> select_profesor_list = profesorRepository.findByNombre(select_profesor);
			for (Profesor i : select_profesor_list) {
				select_profesor1 = i.getId();
				select_profesor = String.valueOf(select_profesor1);

			}
		}


		

		model.addAttribute("alumno_activo", alumnoRepository.countByStatus("Activo"));
		model.addAttribute("alumno_baja_temporal", alumnoRepository.countByStatus("BT"));
		model.addAttribute("alumno_baja_permanente", alumnoRepository.countByStatus("BP"));
		model.addAttribute("alumno_proceso_graduacion", alumnoRepository.countByStatus("PG"));
		model.addAttribute("alumno_graduado", alumnoRepository.countByStatus("G"));
		
		model.addAttribute("num_profesor_sni", profesorRepository.countBySni("Candidato a Investigador Nacional"));
		model.addAttribute("num_profesor_sni1", profesorRepository.countBySni("Investigador Nacional (Nivel 1)"));
		model.addAttribute("num_profesor_sni2", profesorRepository.countBySni("Investigador Nacional (Nivel 2)"));
		model.addAttribute("num_profesor_sni3", profesorRepository.countBySni("Investigador Nacional (Nivel 3)"));
		model.addAttribute("num_profesor_sni4", profesorRepository.countBySni("Investigador Nacional Emerito"));

		String linea = "";
		if (select_linea_investiga.equals("0")) {
			linea = "%";
		} else {
			linea = select_linea_investiga;
		}
		
		float a1;
		float a2;
		float a11;
		float a3;
		float a4;
		float a5;
		float a6;
		Long depto = null;
		if (select_departamento.equals("0")) {
			model.addAttribute("num_tesis_desarrollo", tesisRepository.countByStatusAndLineaLike("TTD", linea));
			model.addAttribute("num_tesis_terminadas_3", tesisRepository.countByStatusAndLineaLike("NTT", linea));
			model.addAttribute("num_tesis_terminadas_2_5", tesisRepository.countByStatusAndLineaLike("NTP", linea));
			model.addAttribute("num_tesis_extemporaneas", tesisRepository.countByStatusAndLineaLike("TTX", linea));
			model.addAttribute("num_tesis_registradas", tesisRepository.countByStatusAndLineaLike("TTR", linea));
			model.addAttribute("num_tesis_no_terminada", tesisRepository.countByStatusAndLineaLike("TTN", linea));
			model.addAttribute("num_tesis_extemporaneas_desarrollo", tesisRepository.countByStatusAndLineaLike("TXD", linea));

			 a1 = tesisRepository.countByStatusAndLineaLike("TTD", linea);
			 a2 = tesisRepository.countByStatusAndLineaLike("NTT", linea);
			 a11 = tesisRepository.countByStatusAndLineaLike("NTP", linea);
			 a3 = tesisRepository.countByStatusAndLineaLike("TTX", linea);
			 a4 = tesisRepository.countByStatusAndLineaLike("TTR", linea);
			 a5 = tesisRepository.countByStatusAndLineaLike("TTN", linea);
			 a6 = tesisRepository.countByStatusAndLineaLike("TXD", linea);
			
		} else {
			List<Departamento> d1 = departamentoRepository.findByNombredepartamento(select_departamento);
			depto = d1.get(0).getId();

			model.addAttribute("num_tesis_desarrollo",tesisRepository.findByDepartamento_idAndStatusAndLineaLike(d1.get(0).getId(),"TTD", linea).size());
			model.addAttribute("num_tesis_terminadas_3", tesisRepository.findByDepartamento_idAndStatusAndLineaLike(d1.get(0).getId(),"NTT", linea).size());
			model.addAttribute("num_tesis_terminadas_2_5", tesisRepository.findByDepartamento_idAndStatusAndLineaLike(d1.get(0).getId(),"NTP", linea).size());
			model.addAttribute("num_tesis_extemporaneas", tesisRepository.findByDepartamento_idAndStatusAndLineaLike(d1.get(0).getId(),"TTX", linea).size());
			model.addAttribute("num_tesis_registradas", tesisRepository.findByDepartamento_idAndStatusAndLineaLike(d1.get(0).getId(),"TTR", linea).size());
			model.addAttribute("num_tesis_no_terminada", tesisRepository.findByDepartamento_idAndStatusAndLineaLike(d1.get(0).getId(),"TTN", linea).size());
			model.addAttribute("num_tesis_extemporaneas_desarrollo", tesisRepository.findByDepartamento_idAndStatusAndLineaLike(d1.get(0).getId(),"TXD", linea).size());

			 a1 = tesisRepository.findByDepartamento_idAndStatusAndLineaLike(d1.get(0).getId(),"TTD", linea).size();
			 a2 = tesisRepository.findByDepartamento_idAndStatusAndLineaLike(d1.get(0).getId(),"NTT", linea).size();
			 a11 = tesisRepository.findByDepartamento_idAndStatusAndLineaLike(d1.get(0).getId(),"NTP", linea).size();
			 a3 = tesisRepository.findByDepartamento_idAndStatusAndLineaLike(d1.get(0).getId(),"TTX", linea).size();
			 a4 = tesisRepository.findByDepartamento_idAndStatusAndLineaLike(d1.get(0).getId(),"TTR", linea).size();
			 a5 = tesisRepository.findByDepartamento_idAndStatusAndLineaLike(d1.get(0).getId(),"TTN", linea).size();
			 a6 = tesisRepository.findByDepartamento_idAndStatusAndLineaLike(d1.get(0).getId(),"TXD", linea).size();
		}
		
		
		float a7 = alumnoRepository.countByStatus("BP");
		float a8 = alumnoRepository.countByStatus("G");
		float a9 = alumnoRepository.count();
		float a10 = alumnoRepository.countByStatus("GX");

		long ef = (long) (100 * (a2 / (a4 - a1))); // eficiencia terminal Estudiantes Registrados tiempos Conacyt
		float tg1 = a2 + a3;
		long tg2 = (long) (100 * (tg1 / (a4 - a1))); // taza de graduados Estudiantes Registrados tiempos conacyt
		long ge = (long) (100 * (a3 / (a4 - a1))); // graduados extemporaneos Estudiantes Registrados tiempos conacyt
		// long efgc = (long) (100*(a2/(a4-a1))); //eficiencia terminal Estudiantes
		// Registrados tiempo cenidet (2.5 años)

		long efg = (long) (100 * (a2 / (a4 - a5 - a1)));
		// eficiencia terminal global Estudiantes Permanentes tiempos conacyt
		long gep = (long) (100 * (a3 / (a4 - a5 - a1))); // graduados extemporaneos Estudiantes Permanentes tiempos
															// conacyt
		// float tg1 = a2+a3;
		long tgp = (long) (100 * (a3 / (a4 - a5 - a1))); // taza de graduados Estudiantes Permanetes tiempos conacyt

		model.addAttribute("eficiencia_terminal", ef);// penultimo
		model.addAttribute("taza_graduados", tg2); // penultimo
		model.addAttribute("graduados_extemporaneos", ge);
		model.addAttribute("eficiencia_terminal_global", efg);
		model.addAttribute("graduados_extemporaneos_conacyt", gep);
		model.addAttribute("taza_graduados_permanentes", tgp);
		// model.addAttribute("eficiencia_global_cenidet", efgc);
		// model.addAttribute("total_graduados", tesisRepository.countByStatus(2+3));

//////////programado desde aqui 22.09
		
		String tesis_fecha_inicio = null;
		String tesis_fecha_final = null;
		String tesis_status = null;
		Long tesis_profesor1 = null;
		String tesis_profesor = null;
		Long tesis_departamento1 = null;
		String tesis_departamento = null;
		String tesis_linea_invest = null;
		String respuesta = null;
		float ntd_3years = 0;
		float ntt_3years = 0;
		float ntt_2_5years = 0;
		float ntp_2_5years = 0;
		float ntb_total = 0;
		float ttr = tesisRepository.count(); // total_alumnos ETG = 100*(NTT / (NTR-NTD))
		float ntx = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		Date select_fechainicio;
		Date select_secondDate;
		
		List<Tesis> lista_tesis;
		if (select_departamento.equals("0")) {
			lista_tesis = tesisRepository.findByLineaLike(linea);
		}else {
			lista_tesis = tesisRepository.findByDepartamento_idAndLineaLike(depto, linea);
		}
		List<Tesis> lista_tesis_all = tesisRepository.findAll();
		
		ArrayList profesores = new ArrayList();
		ArrayList lineas = new ArrayList();
		ArrayList departamentos = new ArrayList();
		for (Tesis i : lista_tesis_all) {
			profesores.add(i.getProfesor().getNombre());
			lineas.add(i.getLinea());
			departamentos.add(i.getDepartamento().getNombredepartamento());
			
		}

		
		
		Set<String> hashSet = new HashSet<String>(lineas);
		lineas.clear();
		lineas.addAll(hashSet);

		Set<String> hashSet2 = new HashSet<String>(profesores);
		profesores.clear();
		profesores.addAll(hashSet2);

		Set<String> hashSet3 = new HashSet<String>(departamentos);
		departamentos.clear();
		departamentos.addAll(hashSet3);

		
		model.addAttribute("Profesores", profesores);
		model.addAttribute("linea_invest", lineas);
		model.addAttribute("Departamentos", departamentos);

		@SuppressWarnings("rawtypes")
		ArrayList lista_3years = null;
		@SuppressWarnings("rawtypes")
		ArrayList lista_2_5years = null;

		for (Tesis i : lista_tesis) {
			tesis_fecha_inicio = i.getFechainicio();
			tesis_fecha_final = i.getFechaprobfin();
			tesis_profesor1 = i.getProfesor().getId();
			tesis_departamento1 = i.getDepartamento().getId();

			tesis_profesor = String.valueOf(tesis_profesor1);
			tesis_departamento = String.valueOf(tesis_departamento1);
			tesis_linea_invest = i.getLinea();

			tesis_status = i.getStatus();
			Date firstDate = sdf.parse(tesis_fecha_inicio);
			Date secondDate = sdf.parse(tesis_fecha_final);
			LocalDate fecha1 = convertLocaldateToDate(firstDate);
			LocalDate fecha2 = convertLocaldateToDate(secondDate);
			Period periodo = Period.between(fecha1, fecha2);
			String años = periodo.getYears() + "." + periodo.getMonths();
			String if_años = periodo.getYears() + "." + periodo.getMonths() + "." + periodo.getDays();
			float aaa = Float.parseFloat(años);

			if (fechainicio.equals("0")) {

				if (select_profesor.equals("0")) {

					if (select_linea_investiga.equals("0")) {
						if (if_años.equals("3.0.0")) {
							if (tesis_status.equals("TTD")) {
								ntd_3years = ntd_3years + 1;
							}
							if (tesis_status.equals("NTT")) {
								ntt_3years = ntt_3years + 1;
							}
						}
						;

						if (tesis_status.equals("TTX")) {
							if (aaa > 3.0) {
								ntx = ntx + 1;
							}
							;
						}

						if (if_años.equals("2.5.0")) {
							if (tesis_status.equals("NTP")) {
								ntp_2_5years = ntp_2_5years + 1;
							}
							if (tesis_status.equals("NTP")) {
								ntt_2_5years = ntt_2_5years + 1;
							}
						}

						if (tesis_status.equals("TTN")) {
							ntb_total = ntb_total + 1;
						}
					} else
					{ 
						
						if (tesis_linea_invest.equals(select_linea_investiga))// linea verdadera
						{
							if (if_años.equals("3.0.0")) {
								if (tesis_status.equals("TTD")) {
									ntd_3years = ntd_3years + 1;
								}
								if (tesis_status.equals("NTT")) {
									ntt_3years = ntt_3years + 1;
								}
							}
							;

							if (tesis_status.equals("TTX")) {
								if (aaa > 3.0) {
									ntx = ntx + 1;
								}
								;
							}

							if (if_años.equals("2.5.0")) {
								if (tesis_status.equals("NTP")) {
									ntp_2_5years = ntp_2_5years + 1;
								}
								if (tesis_status.equals("NTP")) {
									ntt_2_5years = ntt_2_5years + 1;
								}
							}

							if (tesis_status.equals("TTN")) {
								ntb_total = ntb_total + 1;
							}
						}
						;
					}
					;

				} else {

					if (tesis_profesor.equals(select_profesor)) {

						if (select_linea_investiga.equals("0"))// linea falso
						{
							if (if_años.equals("3.0.0")) {
								if (tesis_status.equals("TTD")) {
									ntd_3years = ntd_3years + 1;
								}
								if (tesis_status.equals("NTT")) {
									ntt_3years = ntt_3years + 1;
								}
							}
							;

							if (tesis_status.equals("TTX")) {
								if (aaa > 3.0) {
									ntx = ntx + 1;
								}
								;
							}

							if (if_años.equals("2.5.0")) {
								if (tesis_status.equals("NTP")) {
									ntp_2_5years = ntp_2_5years + 1;
								}
								if (tesis_status.equals("NTP")) {
									ntt_2_5years = ntt_2_5years + 1;
								}
							}

							if (tesis_status.equals("TTN")) {
								ntb_total = ntb_total + 1;
							}
						} else {

							if (tesis_linea_invest.equals(select_linea_investiga))// linea verdadera
							{
								if (if_años.equals("3.0.0")) {
									if (tesis_status.equals("TTD")) {
										ntd_3years = ntd_3years + 1;
									}
									if (tesis_status.equals("NTT")) {
										ntt_3years = ntt_3years + 1;
									}
								}
								;

								if (tesis_status.equals("TTX")) {
									if (aaa > 3.0) {
										ntx = ntx + 1;
									}
									;
								}

								if (if_años.equals("2.5.0")) {
									if (tesis_status.equals("NTP")) {
										ntp_2_5years = ntp_2_5years + 1;
									}
									if (tesis_status.equals("NTP")) {
										ntt_2_5years = ntt_2_5years + 1;
									}
								}

								if (tesis_status.equals("TTN")) {
									ntb_total = ntb_total + 1;
								}
							}
							;
						}
						;
					}
					;

				}
				;

				respuesta = "";

			}

			else {

				select_fechainicio = sdf.parse(fechainicio);
				select_secondDate = sdf.parse(fechafinal);
				long diff = firstDate.getTime() - select_fechainicio.getTime();
				TimeUnit time = TimeUnit.DAYS;

				long diffrence = time.convert(diff, TimeUnit.MILLISECONDS);
				if (diffrence >= 0) {

					if (select_profesor.equals("0")) {

						if (select_linea_investiga.equals("0")) {
							if (if_años.equals("3.0.0")) {
								if (tesis_status.equals("TTD")) {
									ntd_3years = ntd_3years + 1;
								}
								if (tesis_status.equals("NTT")) {
									ntt_3years = ntt_3years + 1;
								}
							}
							;

							if (tesis_status.equals("TTX")) {
								if (aaa > 3.0) {
									ntx = ntx + 1;
								}
								;
							}

							if (if_años.equals("2.5.0")) {
								if (tesis_status.equals("NTP")) {
									ntp_2_5years = ntp_2_5years + 1;
								}
								if (tesis_status.equals("NTP")) {
									ntt_2_5years = ntt_2_5years + 1;
								}
							}

							if (tesis_status.equals("TTN")) {
								ntb_total = ntb_total + 1;
							}
						} else {
							if (tesis_linea_invest.equals(select_linea_investiga))// linea verdadera
							{
								if (if_años.equals("3.0.0")) {
									if (tesis_status.equals("TTD")) {
										ntd_3years = ntd_3years + 1;
									}
									if (tesis_status.equals("NTT")) {
										ntt_3years = ntt_3years + 1;
									}
								}
								;

								if (tesis_status.equals("TTX")) {
									if (aaa > 3.0) {
										ntx = ntx + 1;
									}
									;
								}

								if (if_años.equals("2.5.0")) {
									if (tesis_status.equals("NTP")) {
										ntp_2_5years = ntp_2_5years + 1;
									}
									if (tesis_status.equals("NTP")) {
										ntt_2_5years = ntt_2_5years + 1;
									}
								}

								if (tesis_status.equals("TTN")) {
									ntb_total = ntb_total + 1;
								}
							}
							;
						}
						;

					} else {

						if (select_linea_investiga.equals("0"))// linea falso
						{
							if (if_años.equals("3.0.0")) {
								if (tesis_status.equals("TTD")) {
									ntd_3years = ntd_3years + 1;
								}
								if (tesis_status.equals("NTT")) {
									ntt_3years = ntt_3years + 1;
								}
							}
							;

							if (tesis_status.equals("TTX")) {
								if (aaa > 3.0) {
									ntx = ntx + 1;
								}
								;
							}

							if (if_años.equals("2.5.0")) {
								if (tesis_status.equals("NTP")) {
									ntp_2_5years = ntp_2_5years + 1;
								}
								if (tesis_status.equals("NTP")) {
									ntt_2_5years = ntt_2_5years + 1;
								}
							}

							if (tesis_status.equals("TTN")) {
								ntb_total = ntb_total + 1;
							}
						} else {

							if (tesis_linea_invest.equals(select_linea_investiga))// linea verdadera
							{
								if (if_años.equals("3.0.0")) {
									if (tesis_status.equals("TTD")) {
										ntd_3years = ntd_3years + 1;
									}
									if (tesis_status.equals("NTT")) {
										ntt_3years = ntt_3years + 1;
									}
								}
								;

								if (tesis_status.equals("TTX")) {
									if (aaa > 3.0) {
										ntx = ntx + 1;
									}
									;
								}

								if (if_años.equals("2.5.0")) {
									if (tesis_status.equals("NTP")) {
										ntp_2_5years = ntp_2_5years + 1;
									}
									if (tesis_status.equals("NTP")) {
										ntt_2_5years = ntt_2_5years + 1;
									}
								}

								if (tesis_status.equals("TTN")) {
									ntb_total = ntb_total + 1;
								}
							}
							;
						}
						;

					}
					;

					respuesta = "Graficos filtrados desde el " + fechainicio + " hasta el " + fechafinal + ":";

				}
				;

			}
			;

			model.addAttribute("respuesta", respuesta);
		}
		;

		float etg_3years_er = 100 * (ntt_3years / (ttr - ntd_3years)); // Eficiencia Terminal Global en 3 años (de
																		// estudiantes registrados)
		float etp_3years_er = etg_3years_er / ntt_3years; // Eficiencia Terminal Promedio en 3 años (de estudiantes
															// registrados)
		float age_3years_er = 100 * (ntx / (ttr - ntd_3years)); // aaaaaaaaaaaa Alumnos Graduados Extemporáneos en 3
																// años (de estudiantes registrados)
		float tag_3years_er = ntt_3years + ntx; // aaaaaaaaaaaa Total de Alumnos Graduados en 3 años (estudiantes
												// registrados)
		float tgg_3years_er = 100 * (tag_3years_er / (ttr - ntd_3years)); // Tasa de Graduación Global en 3 años
																			// (estudiantes registrados)
		float tgp_3years_er = tgg_3years_er / tag_3years_er; // Tasa de Graduación Promedio en 3 años (estudiantes
																// registrados)

		float etg_2j5years_er = 100 * (ntp_2_5years / (ttr - ntd_3years)); // Eficiencia Terminal Global en 2.5 años (de
																			// estudiantes registrados)
		float etp_2j5years_er = etg_2j5years_er / ntp_2_5years; // Eficiencia Terminal Promedio en 2.5 años (de
																// estudiantes registrados)
		float age_2j5years_er = 100 * (ntx / (ttr - ntd_3years)); // Alumnos Graduados Extemporáneos en 2.5 años (de
																	// estudiantes registrados)
		float tag_2j5years_er = ntt_2_5years + ntx; // Total de Alumnos Graduados en 2.5 años (estudiantes registrados)
		float tgg_2j5years_er = 100 * (tag_2j5years_er / (ttr - ntd_3years)); // Tasa de Graduación Global en 2.5 años
																				// (estudiantes registrados)
		float tgp_2j5years_er = tgg_2j5years_er / tag_2j5years_er; // Tasa de Graduación Promedio en 2.5 años
																	// (estudiantes registrados)

		float etg_3years_ep = 100 * (ntt_3years / (ttr - ntb_total - ntd_3years)); // Eficiencia Terminal Global en 3
																					// años (de estudiantes permanentes)
		float etp_3years_ep = etg_3years_ep / ntt_3years; // Eficiencia Terminal Promedio en 3 años (de estudiantes
															// permanentes)
		float age_3years_ep = 100 * (ntx / (ttr - ntb_total - ntd_3years)); // Alumnos Graduados Extemporáneos en 3 años
																			// (de estudiantes permanentes)
		float tag_3years_ep = ntt_3years + ntx; // Total de Alumnos Graduados en 3 años (estudiantes permanentes)
		float tgg_3years_ep = 100 * (tag_3years_ep / (ttr - ntb_total - ntd_3years)); // Tasa de Graduación Global en 3
																						// años (estudiantes
																						// permanentes)
		float tgp_3years_ep = tgg_3years_ep / tag_3years_ep; // Tasa de Graduación Promedio en 3 años (estudiantes
																// permanentes)

		float etg_2j5years_ep = 100 * (ntp_2_5years / (ttr - ntb_total - ntd_3years)); // Eficiencia Terminal Global en
																						// 2.5 años (de estudiantes
																						// permanentes)
		float etp_2j5years_ep = etg_2j5years_ep / ntp_2_5years; // Eficiencia Terminal Promedio en 2.5 años (de
																// estudiantes permanentes)
		float age_2j5years_ep = 100 * (ntx / (ttr - ntb_total - ntd_3years)); // Alumnos Graduados Extemporáneos en 2.5
																				// años (de estudiantes permanentes)
		float tag_2j5years_ep = ntt_2_5years + ntx; // Total de Alumnos Graduados en 2.5 años (estudiantes permanentes)
		float tgg_2j5years_ep = 100 * (tag_2j5years_ep / (ttr - ntb_total - ntd_3years)); // Tasa de Graduación Global
																							// en 2.5 años (estudiantes
																							// permanentes)
		float tgp_2j5years_ep = tgg_2j5years_ep / tag_2j5years_ep; // Tasa de Graduación Promedio en 2.5 años
																	// (estudiantes permanentes)

		float tpg = 3 / ntt_3years;

		model.addAttribute("etg_3years_er", etg_3years_er);
		model.addAttribute("etp_3years_er", etp_3years_er);
		model.addAttribute("age_3years_er", age_3years_er);
		model.addAttribute("tag_3years_er", tag_3years_er);
		model.addAttribute("tgg_3years_er", tgg_3years_er);
		model.addAttribute("tgp_3years_er", tgp_3years_er);

		model.addAttribute("etg_2j5years_er", etg_2j5years_er);
		model.addAttribute("etp_2j5years_er", etp_2j5years_er);
		model.addAttribute("age_2j5years_er", age_2j5years_er);
		model.addAttribute("tag_2j5years_er", tag_2j5years_er);
		model.addAttribute("tgg_2j5years_er", tgg_2j5years_er);
		model.addAttribute("tgp_2j5years_er", tgp_2j5years_er);

		model.addAttribute("etg_3years_ep", etg_3years_ep);
		model.addAttribute("etp_3years_ep", etp_3years_ep);
		model.addAttribute("age_3years_ep", age_3years_ep);
		model.addAttribute("tag_3years_ep", tag_3years_ep);
		model.addAttribute("tgg_3years_ep", tgg_3years_ep);
		model.addAttribute("tgp_3years_ep", tgp_3years_ep);

		model.addAttribute("etg_2j5years_ep", etg_2j5years_ep);
		model.addAttribute("etp_2j5years_ep", etp_2j5years_ep);
		model.addAttribute("age_2j5years_ep", age_2j5years_ep);
		model.addAttribute("tag_2j5years_ep", tag_2j5years_ep);
		model.addAttribute("tgg_2j5years_ep", tgg_2j5years_ep);
		model.addAttribute("tgp_2j5years_ep", tgp_2j5years_ep);

		return "index_Estadisticas";
	}

	public LocalDate convertLocaldateToDate(Date dateToConvert) {
		return new java.sql.Date(dateToConvert.getTime()).toLocalDate();
	}


}

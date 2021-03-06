package projecto.data;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import projecto.model.*;
import projecto.repositories.*;

import java.time.LocalDate;
import java.util.Arrays;

@Component
public class Inicializacao implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ColaboradorRepository colaboradorRepository;

    @Autowired
    private ProjetoRepository projetoRepository;
    @Autowired
    private TarefaRepository tarefaRepository;
    @Autowired
    private FuncaoColaboradorRepository funcaoColaboradorRepository;


    @SneakyThrows
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        FuncaoColaborador AJUNIOR = new FuncaoColaborador(1, "Analista Junior", 20.0);
        FuncaoColaborador ASENIOR = new FuncaoColaborador(2, "Analista Senior", 80.0);
        FuncaoColaborador DJUNIOR = new FuncaoColaborador(3, "Desenvolvedor Junior", 10.0);
        FuncaoColaborador DSENIOR = new FuncaoColaborador(4, "Desenvolvedor Senior", 40.0);
        FuncaoColaborador GPROJETO = new FuncaoColaborador(5, "Gestor de Projecto", 100.0);
        funcaoColaboradorRepository.saveAll(Arrays.asList(AJUNIOR, ASENIOR, DJUNIOR, DSENIOR, GPROJETO));

        Colaborador c1 = new Colaborador("Fábio Pinto", AJUNIOR.getCod());
        Colaborador c2 = new Colaborador("Rui Moreira", ASENIOR.getCod());
        Colaborador c3 = new Colaborador("Leonel Ferreira", DSENIOR.getCod());
        Colaborador c4 = new Colaborador("Jorge Ferreira", DJUNIOR.getCod());
        Colaborador c5 = new Colaborador("Tiago Correia", GPROJETO.getCod());
        colaboradorRepository.saveAll(Arrays.asList(c1, c2, c3, c4, c5));

        Cliente cli1 = new Cliente("Filipe Silva", "123");
        Cliente cli2 = new Cliente("António Pinto", "41231");
        Cliente cli3 = new Cliente("Rodrigo Texeira", "12312");
        Cliente cli4 = new Cliente("Joana Pereira", "1231415");
        Cliente cli5 = new Cliente("Joana Costa", "125412");

        clienteRepository.saveAll(Arrays.asList(cli1, cli2, cli3, cli4, cli5));

        Projeto p1 = new Projeto("Gestor de documentos", cli1, LocalDate.of(2020, 9, 20), LocalDate.of(2020, 9, 20));
        Projeto p2 = new Projeto("Gestão de RH", cli2, LocalDate.of(2020, 9, 20), LocalDate.of(2020, 9, 20));
        Projeto p3 = new Projeto("Gestão de Vencimentos", cli3, LocalDate.of(2020, 9, 20), LocalDate.of(2020, 9, 20));
        Projeto p4 = new Projeto("Gestão de Viaturas", cli4, LocalDate.of(2020, 9, 20), LocalDate.of(2020, 9, 20));
        Projeto p5 = new Projeto("Aplicação mobile", cli5, LocalDate.of(2020, 9, 20), LocalDate.of(2020, 9, 20));
        Projeto p6 = new Projeto("Aplicação mobile", cli1, LocalDate.of(2020, 9, 20), LocalDate.of(2020, 9, 20));
        Projeto p7 = new Projeto("Aplicação mobile", cli1, LocalDate.of(2020, 9, 20), LocalDate.of(2020, 9, 20));

        cli1.getListaProjectos().addAll(Arrays.asList(p1, p6));
        cli2.getListaProjectos().addAll(Arrays.asList(p2));
        cli3.getListaProjectos().addAll(Arrays.asList(p3));
        cli4.getListaProjectos().addAll(Arrays.asList(p4));
        cli5.getListaProjectos().addAll(Arrays.asList(p5));
        cli5.addProjecto(p7);
      //  clienteRepository.saveAll(Arrays.asList(cli1, cli2, cli3, cli4, cli5));


        projetoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7));

        Tarefa t1 = new Tarefa(LocalDate.of(2020, 6, 13), LocalDate.of(2020, 9, 20), "Software Test Engineer I", c3, p5, 91);
        Tarefa t2 = new Tarefa(LocalDate.of(2020, 8, 7), LocalDate.of(2020, 8, 17), "VP Accounting", c4, p5, 67);
        Tarefa t3 = new Tarefa(LocalDate.of(2020, 1, 31), LocalDate.of(2020, 9, 3), "Research Assistant I", c4, p7, 178);
        Tarefa t4 = new Tarefa(LocalDate.of(2020, 2, 19), LocalDate.of(2020, 7, 10), "VP Quality Control", c3, p7, 81);
        Tarefa t5 = new Tarefa(LocalDate.of(2020, 3, 29), LocalDate.of(2020, 12, 11), "Community Outreach Specialist", c1, p5, 194);
        Tarefa t6 = new Tarefa(LocalDate.of(2020, 6, 15), LocalDate.of(2020, 10, 30), "Automation Specialist II", c1, p1, 151);
        Tarefa t7 = new Tarefa(LocalDate.of(2020, 3, 20), LocalDate.of(2020, 6, 24), "Budget/Accounting Analyst III", c1, p3, 5);
        Tarefa t8 = new Tarefa(LocalDate.of(2020, 3, 19), LocalDate.of(2020, 11, 16), "Structural Engineer", c1, p5, 150);
        Tarefa t9 = new Tarefa(LocalDate.of(2020, 1, 14), LocalDate.of(2020, 4, 29), "Financial Advisor", c3, p7, 57);
        Tarefa t10 = new Tarefa(LocalDate.of(2020, 6, 14), LocalDate.of(2020, 11, 3), "Senior Editor", c5, p5, 10);
        Tarefa t11 = new Tarefa(LocalDate.of(2020, 4, 2), LocalDate.of(2020, 10, 22), "Junior Executive", c4, p4, 114);
        Tarefa t12 = new Tarefa(LocalDate.of(2020, 11, 1), LocalDate.of(2020, 12, 2), "Civil Engineer", c5, p5, 83);
        Tarefa t13 = new Tarefa(LocalDate.of(2020, 11, 11), LocalDate.of(2020, 11, 13), "Mechanical Systems Engineer", c2, p7, 62);
        Tarefa t14 = new Tarefa(LocalDate.of(2020, 4, 26), LocalDate.of(2020, 7, 1), "Automation Specialist I", c1, p5, 17);
        Tarefa t15 = new Tarefa(LocalDate.of(2020, 8, 23), LocalDate.of(2020, 10, 18), "Environmental Tech", c4, p7, 68);
        Tarefa t16 = new Tarefa(LocalDate.of(2020, 7, 19), LocalDate.of(2020, 9, 28), "Biostatistician IV", c1, p5, 85);
        Tarefa t17 = new Tarefa(LocalDate.of(2020, 1, 21), LocalDate.of(2020, 4, 18), "Graphic Designer", c2, p3, 88);
        Tarefa t18 = new Tarefa(LocalDate.of(2020, 5, 26), LocalDate.of(2020, 6, 12), "Systems Administrator III", c3, p5, 54);
        Tarefa t19 = new Tarefa(LocalDate.of(2020, 3, 13), LocalDate.of(2020, 3, 27), "Structural Engineer", c3, p5, 63);
        Tarefa t20 = new Tarefa(LocalDate.of(2020, 8, 24), LocalDate.of(2020, 9, 9), "Financial Analyst", c5, p3, 155);
        Tarefa t21 = new Tarefa(LocalDate.of(2020, 6, 22), LocalDate.of(2020, 11, 4), "Research Nurse", c4, p6, 55);
        Tarefa t22 = new Tarefa(LocalDate.of(2020, 1, 25), LocalDate.of(2020, 8, 26), "Operator", c2, p4, 39);
        Tarefa t23 = new Tarefa(LocalDate.of(2020, 7, 17), LocalDate.of(2020, 9, 12), "Human Resources Manager", c2, p2, 84);
        Tarefa t24 = new Tarefa(LocalDate.of(2020, 2, 21), LocalDate.of(2020, 3, 16), "Actuary", c1, p1, 162);
        Tarefa t25 = new Tarefa(LocalDate.of(2020, 3, 11), LocalDate.of(2020, 7, 1), "Marketing Assistant", c1, p5, 196);
        Tarefa t26 = new Tarefa(LocalDate.of(2020, 10, 20), LocalDate.of(2020, 12, 19), "Environmental Specialist", c1, p6, 173);
        Tarefa t27 = new Tarefa(LocalDate.of(2020, 7, 27), LocalDate.of(2020, 8, 28), "Junior Executive", c2, p2, 6);
        Tarefa t28 = new Tarefa(LocalDate.of(2020, 1, 19), LocalDate.of(2020, 4, 24), "Administrative Assistant II", c4, p2, 31);
        Tarefa t29 = new Tarefa(LocalDate.of(2020, 3, 22), LocalDate.of(2020, 6, 4), "Occupational Therapist", c3, p5, 9);
        Tarefa t30 = new Tarefa(LocalDate.of(2020, 3, 22), LocalDate.of(2020, 6, 14), "Account Representative I", c4, p5, 91);
        Tarefa t31 = new Tarefa(LocalDate.of(2020, 6, 28), LocalDate.of(2020, 10, 31), "Statistician IV", c3, p7, 14);
        Tarefa t32 = new Tarefa(LocalDate.of(2020, 2, 19), LocalDate.of(2020, 3, 3), "Nuclear Power Engineer", c4, p2, 89);
        Tarefa t33 = new Tarefa(LocalDate.of(2020, 1, 20), LocalDate.of(2020, 11, 1), "Design Engineer", c3, p7, 160);
        Tarefa t34 = new Tarefa(LocalDate.of(2020, 10, 21), LocalDate.of(2020, 12, 7), "Civil Engineer", c5, p1, 153);
        Tarefa t35 = new Tarefa(LocalDate.of(2020, 10, 20), LocalDate.of(2020, 12, 1), "VP Accounting", c4, p2, 11);
        Tarefa t36 = new Tarefa(LocalDate.of(2020, 1, 14), LocalDate.of(2020, 12, 27), "Research Nurse", c1, p6, 44);
        Tarefa t37 = new Tarefa(LocalDate.of(2020, 4, 15), LocalDate.of(2020, 5, 5), "Account Coordinator", c3, p6, 88);
        Tarefa t38 = new Tarefa(LocalDate.of(2020, 9, 21), LocalDate.of(2020, 10, 3), "VP Sales", c1, p2, 74);
        Tarefa t39 = new Tarefa(LocalDate.of(2020, 6, 16), LocalDate.of(2020, 12, 18), "Programmer IV", c2, p4, 127);
        Tarefa t40 = new Tarefa(LocalDate.of(2020, 4, 14), LocalDate.of(2020, 9, 13), "Biostatistician III", c3, p4, 8);
        Tarefa t41 = new Tarefa(LocalDate.of(2020, 7, 27), LocalDate.of(2020, 7, 28), "Mechanical Systems Engineer", c5, p3, 96);
        Tarefa t42 = new Tarefa(LocalDate.of(2020, 4, 6), LocalDate.of(2020, 8, 1), "Professor", c3, p2, 49);
        Tarefa t43 = new Tarefa(LocalDate.of(2020, 7, 1), LocalDate.of(2020, 7, 28), "Project Manager", c3, p3, 29);
        Tarefa t44 = new Tarefa(LocalDate.of(2020, 4, 22), LocalDate.of(2020, 8, 7), "Web Designer II", c2, p5, 30);
        Tarefa t45 = new Tarefa(LocalDate.of(2020, 5, 10), LocalDate.of(2020, 5, 30), "Community Outreach Specialist", c1, p4, 174);
        Tarefa t46 = new Tarefa(LocalDate.of(2020, 4, 28), LocalDate.of(2020, 10, 6), "Geological Engineer", c2, p4, 178);
        Tarefa t47 = new Tarefa(LocalDate.of(2020, 7, 8), LocalDate.of(2020, 12, 30), "Occupational Therapist", c2, p4, 93);
        Tarefa t48 = new Tarefa(LocalDate.of(2020, 3, 31), LocalDate.of(2020, 11, 19), "Research Nurse", c2, p1, 3);
        Tarefa t49 = new Tarefa(LocalDate.of(2020, 6, 7), LocalDate.of(2020, 8, 28), "Administrative Officer", c3, p5, 101);
        Tarefa t50 = new Tarefa(LocalDate.of(2020, 1, 2), LocalDate.of(2020, 5, 29), "GIS Technical Architect", c3, p6, 175);
        tarefaRepository.saveAll(Arrays.asList(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18, t19, t20, t21, t22, t23, t24, t25, t26, t27, t28, t29, t30, t31, t32, t33, t34, t35, t36, t37, t38, t39, t40, t41, t42, t43, t44, t45, t46, t47, t48, t49, t50));
    }
}

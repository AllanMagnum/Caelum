package br.com.caelum.tarefas.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.caelum.tarefas.dao.JdbcTarefaDao;
import br.com.caelum.tarefas.modelo.Tarefa;

@Controller
public class TarefasController {
	@Autowired
	private JdbcTarefaDao tarefaDao; 
	
	@RequestMapping("novaTarefa")
	public String form(){
		return "tarefa/formulario";
	}
	
	@RequestMapping("adicionaTarefa")
	public String adiciona(@Valid Tarefa tarefa, BindingResult result){
		
		if(result.hasFieldErrors("descricao")){
			return "tarefa/formulario";
		}
		
		tarefaDao.adiciona(tarefa);
		return "redirect:listaTarefa";
	}
	
	@RequestMapping("listaTarefa")
	public String lista(Model model){
		tarefaDao.lista();
		model.addAttribute("tarefas",tarefaDao.lista());
		return "tarefa/lista";
	}
	
	@RequestMapping("alteraTarefa")
	public String altera(Tarefa tarefa){
		tarefaDao.altera(tarefa);
		return "redirect:listaTarefa";
	}
	
	@RequestMapping("removeTarefa")
	public String remove(Tarefa tarefa){
		tarefaDao.remove(tarefa);
		return "redirect:listaTarefa";
	}
	
	@RequestMapping("mostraTarefa")
	public String mostra(Tarefa tarefa, Model model){
		tarefa = tarefaDao.buscaPorId(tarefa.getId());
		model.addAttribute("tarefa", tarefa);
		return "tarefa/mostra";
	}
	
	
}

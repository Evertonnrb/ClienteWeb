package br.com.clienteweb;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.clienteweb.model.Cliente;
import br.com.clienteweb.service.ClienteService;

//abaixo notação para rastrear url
@WebServlet(urlPatterns = { "/cliente", "/controlerCliente", "/clienteServlet" })

public class ClienteServlet extends HttpServlet {
	// inicializando a propriedade da classe
	ClienteService clienteService = new ClienteService();

	public ClienteServlet() {
		System.out.println("Constuindo o servlet...");
	}

	// método init
	@Override
	public void init() throws ServletException {
		// instânciando uma copia do objeto ao carregar o servlet
		// no init
		ClienteService clienteService = new ClienteService();

		System.out.println("Inicializando o servle....");
		super.init();
	}

	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		System.out.println("Chamando o serviço");
		super.service(arg0, arg1);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// System.out.println("Chamado do metodo GET");
		// resp.setCharacterEncoding("UTF-8");

		// resp.getWriter().print("Chamando pelo méodo GET");
		// direcionanado a get
		String i = req.getParameter("i");
		if (i != null && i != "") {
			clienteService.excluir(Integer.parseInt(i));
		}

		RequestDispatcher dispatcher = req.getRequestDispatcher("cliente.jsp");
		// unserindo na lista

		req.setAttribute("lista", clienteService.getTodosClientes());
		// encaminhando

		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// System.out.println("Chamando pelo metodo POST");
		// recebendo o mail
		String email = req.getParameter("email");
		// colocando email em um objeto cliente
		Cliente cli = new Cliente();
		cli.setEmail(email);

		clienteService.cadastrar(cli);
		// adicionando objeto à uma lista
		// lista.add(cli);

		RequestDispatcher dispatcher = req.getRequestDispatcher("cliente.jsp");
		req.setAttribute("msg", "cadastrado com sucesso");
		req.setAttribute("lista", clienteService.getTodosClientes());// pegando
																		// os
																		// clientes
																		// da
																		// classe
																		// ClienteService
		dispatcher.forward(req, resp);
		// resp.setCharacterEncoding("UTF-8");
		// resp.getWriter().print("Chamou pelo método POST "+email+" enviando
		// email");
		// redirecionando para a pagina
		// resp.sendRedirect("cliente");
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().println("Chamndo pelo método DELETE");
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().print("Chamando pelo método PUT");
	}

	@Override
	public void destroy() {
		System.out.println("Destruindo o servlet");
		super.destroy();
	}
}

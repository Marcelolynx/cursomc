package com.marcelo.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.marcelo.cursomc.domain.Categoria;
import com.marcelo.cursomc.domain.Cidade;
import com.marcelo.cursomc.domain.Cliente;
import com.marcelo.cursomc.domain.Endereco;
import com.marcelo.cursomc.domain.Estado;
import com.marcelo.cursomc.domain.EstadoPagamento;
import com.marcelo.cursomc.domain.Pagamento;
import com.marcelo.cursomc.domain.PagamentoComCartao;
import com.marcelo.cursomc.domain.Pedido;
import com.marcelo.cursomc.domain.Produto;
import com.marcelo.cursomc.domain.TipoCliente;
import com.marcelo.cursomc.repositories.CategoriaRepository;
import com.marcelo.cursomc.repositories.CidadeRepository;
import com.marcelo.cursomc.repositories.ClienteRepository;
import com.marcelo.cursomc.repositories.EnderecoRepository;
import com.marcelo.cursomc.repositories.EstadoRepository;
import com.marcelo.cursomc.repositories.PagamentoRepository;
import com.marcelo.cursomc.repositories.PedidoRepository;
import com.marcelo.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;


    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PagamentoRepository pagamentoRepository;

    public static void main(String[] args) {
        SpringApplication.run(CursomcApplication.class, args);


    }

    @Override
    public void run(String... args) throws Exception {
        Categoria cat1 = new Categoria(null, "Sedan");
        Categoria cat2 = new Categoria(null, "Hatch");
        Categoria cat3 = new Categoria(null, "Utilitário");
        Categoria cat4 = new Categoria(null, "SUV");


        Produto p1 = new Produto(null, "Jetta", 128000.00);
        Produto p2 = new Produto(null, "Golf", 112000.00);
        Produto p3 = new Produto(null, "Polo", 78000.00);
        Produto p4 = new Produto(null, "UP", 58000.00);
        Produto p5 = new Produto(null, "Virtus", 78000.00);
        Produto p6 = new Produto(null, "Tiguan", 178000.00);


        cat1.getProdutos().addAll(Arrays.asList(p1, p5));
        cat2.getProdutos().addAll(Arrays.asList(p2, p3, p4));
        cat4.getProdutos().addAll(Arrays.asList(p6));


        p1.getCategorias().addAll(Arrays.asList(cat1));
        p2.getCategorias().addAll(Arrays.asList(cat2));
        p3.getCategorias().addAll(Arrays.asList(cat2));
        p4.getCategorias().addAll(Arrays.asList(cat2));
        p5.getCategorias().addAll(Arrays.asList(cat1));
        p6.getCategorias().addAll(Arrays.asList(cat4));


        categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4));
        produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6));

        Estado est1 = new Estado(null, "Mato Grosso do Sul");
        Estado est2 = new Estado(null, "Mato Grosso");

        Cidade c1 = new Cidade(null, "Campo Grande", est1);
        Cidade c2 = new Cidade(null, "Cuiabá", est2);
        Cidade c3 = new Cidade(null, "Varzea Grande", est2);

        est1.getCidades().addAll(Arrays.asList(c1));
        est2.getCidades().addAll(Arrays.asList(c2, c3));

        estadoRepository.saveAll(Arrays.asList(est1, est2));
        cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));


        Cliente cliente = new Cliente(null, "Maria Clara", "maria_clara@gmail.com", "123151698-00", TipoCliente.PESSOAFISICA);

        cliente.getTelefones().addAll(Arrays.asList("6727363323", "6799938393"));

        Endereco end1 = new Endereco(null, "Rua Mira Flores", "300", "Casa", "Novo Horizonte", "24098-900", cliente, c3);

        cliente.getEnderecos().addAll(Arrays.asList(end1));

        clienteRepository.saveAll(Arrays.asList(cliente));
        enderecoRepository.saveAll(Arrays.asList(end1));

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        Pedido pedido = new Pedido(null, sdf.parse("11/12/2019 16:27"), cliente, end1);

        Pagamento pagamento = new PagamentoComCartao(null, EstadoPagamento.QUITADO, pedido, 6);
        pedido.setPagamento(pagamento);

        cliente.getPedidos().addAll(Arrays.asList(pedido));


        pedidoRepository.saveAll(Arrays.asList(pedido));
        pagamentoRepository.saveAll(Arrays.asList(pagamento));
    }


}
	 
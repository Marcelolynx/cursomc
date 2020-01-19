package com.marcelo.cursomc.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;


import com.marcelo.cursomc.domain.Categoria;
import com.marcelo.cursomc.domain.Cidade;
import com.marcelo.cursomc.domain.Cliente;
import com.marcelo.cursomc.domain.Endereco;
import com.marcelo.cursomc.domain.Estado;
import com.marcelo.cursomc.domain.EstadoPagamento;
import com.marcelo.cursomc.domain.ItemPedido;
import com.marcelo.cursomc.domain.Pagamento;
import com.marcelo.cursomc.domain.PagamentoComBoleto;
import com.marcelo.cursomc.domain.PagamentoComCartao;
import com.marcelo.cursomc.domain.Pedido;
import com.marcelo.cursomc.domain.Produto;
import com.marcelo.cursomc.domain.TipoCliente;
import com.marcelo.cursomc.repositories.CategoriaRepository;
import com.marcelo.cursomc.repositories.CidadeRepository;
import com.marcelo.cursomc.repositories.ClienteRepository;
import com.marcelo.cursomc.repositories.EnderecoRepository;
import com.marcelo.cursomc.repositories.EstadoRepository;
import com.marcelo.cursomc.repositories.ItemPedidoRepository;
import com.marcelo.cursomc.repositories.PagamentoRepository;
import com.marcelo.cursomc.repositories.PedidoRepository;
import com.marcelo.cursomc.repositories.ProdutoRepository;
import org.springframework.stereotype.Service;


@Service
public class DBService {

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


    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    public void instantiateTestDatabase() throws ParseException {

        Categoria cat1 = new Categoria(null, "Sedan");
        Categoria cat2 = new Categoria(null, "Hatch");
        Categoria cat3 = new Categoria(null, "Pick-up");
        Categoria cat4 = new Categoria(null, "SUV");
        Categoria cat5 = new Categoria(null, "Hibrid");
        Categoria cat6 = new Categoria(null, "Eletric");


        Produto p1 = new Produto(null, "Jetta", 128000.00);
        Produto p2 = new Produto(null, "Golf", 112000.00);
        Produto p3 = new Produto(null, "Polo", 78000.00);
        Produto p4 = new Produto(null, "UP", 58000.00);
        Produto p5 = new Produto(null, "Virtus", 78000.00);
        Produto p6 = new Produto(null, "Tiguan", 178000.00);
        Produto p7 = new Produto(null, "Amarok", 200000.00);
        Produto p8 = new Produto(null, "ID.3", 280000.00);
        Produto p9 = new Produto(null, "GOLF-GTE", 250000.00);


        cat1.getProdutos().addAll(Arrays.asList(p1, p5));
        cat2.getProdutos().addAll(Arrays.asList(p2, p3, p4));
        cat4.getProdutos().addAll(Arrays.asList(p6));
        cat3.getProdutos().addAll(Arrays.asList(p7));
        cat5.getProdutos().addAll(Arrays.asList(p9));
        cat6.getProdutos().addAll(Arrays.asList(p8));


        p1.getCategorias().addAll(Arrays.asList(cat1));
        p2.getCategorias().addAll(Arrays.asList(cat2));
        p3.getCategorias().addAll(Arrays.asList(cat2));
        p4.getCategorias().addAll(Arrays.asList(cat2));
        p5.getCategorias().addAll(Arrays.asList(cat1));
        p6.getCategorias().addAll(Arrays.asList(cat4));
        p7.getCategorias().addAll(Arrays.asList(cat3));
        p8.getCategorias().addAll(Arrays.asList(cat6));
        p9.getCategorias().addAll(Arrays.asList(cat5));


        categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4,cat5, cat6));
        produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9));

        Estado est1 = new Estado(null, "Mato Grosso do Sul");
        Estado est2 = new Estado(null, "Mato Grosso");
        Estado est3 = new Estado(null, "Goias");
        Estado est4 = new Estado(null, "Brasília");

        Cidade c1 = new Cidade(null, "Campo Grande", est1);
        Cidade c2 = new Cidade(null, "Cuiabá", est2);
        Cidade c3 = new Cidade(null, "Varzea Grande", est2);
        Cidade c4 = new Cidade(null, "Goiânia", est3);
        Cidade c5 = new Cidade(null, "Brasília", est4);

        est1.getCidades().addAll(Arrays.asList(c1));
        est2.getCidades().addAll(Arrays.asList(c2, c3));

        estadoRepository.saveAll(Arrays.asList(est1, est2, est3, est4));
        cidadeRepository.saveAll(Arrays.asList(c1, c2, c3, c4, c5));


        Cliente cliente = new Cliente( null, "Maria Clara", "maria_clara@gmail.com", "123151698-00",TipoCliente.PESSOAFISICA, "123");
        Cliente cliente2 = new Cliente(null, "João Claudio", "jc_fazendanovalianca@gmail.com", "13298991-00", TipoCliente.PESSOAFISICA, "123");

        cliente.getTelefones().addAll(Arrays.asList("6727363323", "6799938393"));
        cliente2.getTelefones().addAll(Arrays.asList("67992320990", "67984329009"));

        Endereco end1 = new Endereco(null, "Rua Mira Flores", "300", "Casa", "Novo Horizonte", "24098-900", cliente, c3);
        Endereco end2 = new Endereco(null, "Rua Rubens Gilde Camilo", "162", "Casa", "Residencial Damha IV", "79002-440", cliente2, c1);

        cliente.getEnderecos().addAll(Arrays.asList(end1));
        cliente2.getEnderecos().addAll(Arrays.asList(end2));

        clienteRepository.saveAll(Arrays.asList(cliente, cliente2));
        enderecoRepository.saveAll(Arrays.asList(end1, end2));

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        Pedido pedido = new Pedido(null, sdf.parse("11/12/2019 16:27"), cliente, end1);
        Pedido pedido2 = new Pedido(null, sdf.parse("18/12/2019 08:27"), cliente2, end2);

        Pagamento pagamento = new PagamentoComCartao(null, EstadoPagamento.QUITADO, pedido, 6);
        Pagamento pagamento2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, pedido2, sdf.parse("20/01/2019 09:07"), null);

        pedido.setPagamento(pagamento);
        pedido2.setPagamento(pagamento2);

        cliente.getPedidos().addAll(Arrays.asList(pedido));
        cliente2.getPedidos().addAll(Arrays.asList(pedido2));


        pedidoRepository.saveAll(Arrays.asList(pedido, pedido2));
        pagamentoRepository.saveAll(Arrays.asList(pagamento, pagamento2));


        ItemPedido ip1 = new ItemPedido(pedido, p1, 0.00, 1, 200000.00);
        ItemPedido ip2 = new ItemPedido(pedido, p3, 0.00, 1, 80000.00);
        ItemPedido ip3 = new ItemPedido(pedido2,p7, 0.00, 2, 200000.00);

        pedido.getItens().addAll(Arrays.asList(ip1, ip2, ip3));

        p1.getItens().addAll(Arrays.asList(ip1));
        p2.getItens().addAll(Arrays.asList(ip2));
        p7.getItens().addAll(Arrays.asList(ip3));

        itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
    }
}

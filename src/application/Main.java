package application;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Main {
	// acesso das urls da página da ANS

	public static void main(String[] args) throws IOException, InterruptedException {
		String url = "https://www.gov.br/ans/pt-br/assuntos/consumidor/o-que-o-seu-plano-de-saude-deve-cobrir-1/o-que-e-o-rol-de-procedimentos-e-evento-em-saude";
		Document doc = Jsoup.connect(url).get();
		List<Element> chamadas = doc.getElementById("content-core").getElementsByClass("callout");
		List<Element> as = new ArrayList<>();
		List<String> hrefs = new ArrayList<>();
		chamadas.forEach(element -> {
			as.add(element.getElementsByTag("a").first());
		});
		as.forEach(element -> {
			hrefs.add(element.attr("href"));
		});

		hrefs.remove(0);
		hrefs.remove(1);
		
		//as urls dos Anexos numa Lista
		for (String c :hrefs){
			System.out.println(c);
		}
		
	
		//Baixo cada um dos Links em PDF
		String link ="https://www.gov.br/ans/pt-br/arquivos/assuntos/consumidor/o-que-seu-plano-deve-cobrir/Anexo_I_Rol_2021RN_465.2021_RN473_RN478_RN480_RN513_RN536_RN537_RN538_RN539_RN541_RN542_RN544_546.pdf";
		File out = new File("C:\\Users\\Ana Lua\\Desktop\\TESTE1.pdf");
		new Thread(new Download(link,out)).start();
		
		//Conectando os dois (faltou fazer, mas segue a linha de raciocínio):
		//Jogar a parte de cima para um for onde cada "link" recebe o "http://" da Lista String hrefs
		//Usar o String Builder para criar o nome no File dentro de um for tb, para que cada arquivo seja iterado com i da posição
		//Assim, cada pdf vai ser baixado como um doc diferente na mesma pasta. 

	}
}
	

	


import java.util.Scanner;
import java.sql.*;
import java.util.ArrayList;

 
public class Agenda{
		
	public static void main(String args[]){
		
		bd banco 	= new bd();
		Scanner in 	= new Scanner(System.in);
		String item1 	= new String();
 	
		System.out.println("\n\nBem vindo ao programa - Agenda Telefonica Simples\n");
		
		do{
			
			//Menu Principal
			System.out.println("\n\nMenu Principal");
			System.out.println("1 - Listar contatos");
			System.out.println("2 - Cadastrar novo contato");
			System.out.println("3 - Apagar contato");
			System.out.println("4 - Atualizar contato");
			System.out.println("0 - Sair");		
			
			System.out.print("\nDigite o numero da funcao que deseja acessar: ");
			item1 = in.nextLine();
			
			//Funcao  1 - Listar Contatos
			if(item1.equals("1")){
			
				System.out.println("\n\nSeus contatos cadastrados sao:\n\n");
				
				try{
				
					ResultSet rs = banco.selectQuery("select id, name, telefone from agenda");
					boolean existemContatosCadastrados = false;
					while(rs.next()){
				 
						int id 		= rs.getInt("id");
						String nome 	= rs.getString("name");
						String telefone = rs.getString("telefone");
						
						System.out.println("id: " + id);
						System.out.println("nome: " + nome);
						System.out.println("telefone: " + telefone);
						System.out.println("-----------");
						
						existemContatosCadastrados = true;
					
					}
					
					if(!existemContatosCadastrados) System.out.println("Nao existem contatos cadastrados");
					
				}catch (Exception se) {
				
							System.out.println("Erro ao listar contatos.");
							se.printStackTrace();
							System.exit(1);
						
				}
				
				
			
			}
			
			//Funcao 2 - Cadastrar novo contato
			else if(item1.equals("2")){
			
				System.out.print("\n\nDigite o nome do novo contato ou 0 para cancelar: ");
				
				String nome = in.nextLine();
				
				//verifica se o usuário cancelou
				if(!nome.equals("0")){ 
				
					System.out.print("Digite o telefone de "+ nome +" ou 0 para cancelar: ");
					String telefone = in.nextLine();
					
					//verifica se o usuario cancelou
					if(!telefone.equals("0")){
						
						int id = banco.geraChaveAgenda();	
						String query = "insert into agenda values( "+id+" , '" + nome + "', '" + telefone + "' )";
						
						try{
							
							banco.insertUpdateDeleteQuery(query);
							
						} catch (Exception se) {
				
							System.out.println("Erro ao inserir novo cadastro.");
							se.printStackTrace();
							System.exit(1);
						
						}
						
				
					}
				
				}				
			
			}
			
			//Funcao 3 - Apagar Contatos
			
			
			else if(item1.equals("3")){
			
				System.out.println("\n\nSeus contatos cadastrados sao:\n\n");
				
				try{
				
					ResultSet rs = banco.selectQuery("select id, name, telefone from agenda");
					ArrayList<String> ids = new ArrayList<String>();
					
					boolean existemContatosCadastrados = false;
					while(rs.next()){
				 
						Integer id 			= rs.getInt("id");
						
						ids.add( id + ""); //transforma o id em string e armazena no arraylist
						
						String nome 	= rs.getString("name");
						String telefone = rs.getString("telefone");
						
						System.out.println("id: " + id);
						System.out.println("nome: " + nome);
						System.out.println("telefone: " + telefone);
						System.out.println("-----------");
						existemContatosCadastrados = true;
								
					
					}
					
					if(!existemContatosCadastrados){ System.out.println("Nao existem contatos cadastrados");}
					
					System.out.print	("\nDigite o id do contato que deseja apagar ou  0 para cancelar: ");
					String id = in.nextLine();
					
					//verifica se o usuario cancelou
					if(!id.equals("0")){
						
						//verifica se o id existe
						if(ids.contains(id)){
							
							String query = "delete from agenda where id = " + id;
							banco.insertUpdateDeleteQuery(query);
						
						}else{
						
							System.out.println("\n\nDesculpe ID nao cadastrado\n\n");
						
						}
					
					
					}
					
				}catch (Exception se) {
				
							System.out.println("Erro ao listar contatos.");
							se.printStackTrace();
							System.exit(1);
						
				}
			
			}
			//Funcao 4 - Atualizar contato
			
			else if(item1.equals("4")){
			
				
				System.out.println("\n\nSeus contatos cadastrados sao:\n\n");
				
				try{
				
					ResultSet rs = banco.selectQuery("select id, name, telefone from agenda");
					ArrayList<String> ids = new ArrayList<String>();
					boolean existemContatosCadastrados = false;
					while(rs.next()){
				 
						Integer id 			= rs.getInt("id");
						
						ids.add( id + ""); //transforma o id em string e armazena no arraylist
						
						String nome 	= rs.getString("name");
						String telefone = rs.getString("telefone");
						
						System.out.println("id: " + id);
						System.out.println("nome: " + nome);
						System.out.println("telefone: " + telefone);
						System.out.println("-----------");
						existemContatosCadastrados = true;
								
					
					}
					
					if(!existemContatosCadastrados){ System.out.println("Nao existem contatos cadastrados");}
					
					
					System.out.print	("\nDigite o id do contato que deseja atualizar ou  0 para cancelar: ");
					String id = in.nextLine();
					
					//verifica se o usuario cancelou
					if(!id.equals("0")){
						
						//verifica se o id existe
						if(ids.contains(id)){
							
							
							System.out.print("\n\nDigite o novo nome do contato ou 0 para cancelar: ");
				
							String nome = in.nextLine();
				
							//verifica se o usuário cancelou
							if(!nome.equals("0")){ 
				
								System.out.print("Digite o novo telefone de "+ nome +" ou 0 para cancelar: ");
								String telefone = in.nextLine();
					
								//verifica se o usuario cancelou
								if(!telefone.equals("0")){
						
									
									String query = "update  agenda set name = '" + nome + "', telefone = '" + telefone + "' where id = " + id;
									//System.out.println(query);
						
									try{
										
										banco.insertUpdateDeleteQuery(query);
										
									} catch (Exception se) {
							
										System.out.println("Erro ao inserir novo cadastro.");
										se.printStackTrace();
										System.exit(1);
									
									}
						
				
							}		
				
						}
							
						
						}else{
						
							System.out.println("\n\nDesculpe ID nao cadastrado\n\n");
						
						}
					
					
					}
					
				}catch (Exception se) {
				
							System.out.println("Erro ao listar contatos.");
							se.printStackTrace();
							System.exit(1);
						
				}

			
			}
			
			//Funcao 0 - saida da aplicacao
			
			else if(item1.equals("0")){
			
				System.out.println("\n\n\nAte logo !");
			
			}
		
			//Funcao desconhecida
			
			else {
			
				System.out.println("\n\nDesculpe, funcao Desconhecida...\n\n");
			
			}
			
			
			
		}while(!item1.equals("0"));
		
		
	
	}

}

class bd {

	
	private Connection c;
	private Statement stmt;	
	 
	public bd(){
			
			//Tenta encontrar um driver jdbc do derby
			try {
				
			    Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			  
			} catch (ClassNotFoundException cnfe) {
				
			    System.out.println("Driver do banco de dados nao localizado!");
			    cnfe.printStackTrace();
			    System.exit(1);
			    
			}
			
			
			//Cria uma conexao com o banco de dados e cria o banco de dados caso ele nao exista
			try {

				  c = DriverManager.getConnection("jdbc:derby:banco_agenda;create=true");			  		    
				  stmt = c.createStatement();
			  
			} catch (SQLException se) {
				  
			    System.out.println("Não foi possível conectar ao banco de dados.");
			    se.printStackTrace();
			    System.exit(1);
			    
			}
			
			
			//verifica se a tabela já existe no banco. caso não exista cria ela.
			try{
			
				this.selectQuery("select * from agenda");
			
			} catch (Exception se) {
				  
			    System.out.println("\n\nCriando a tabela agenda\n\n");
			    
				//cria tabela caso ela nao exista
				try {
					
					this.insertUpdateDeleteQuery("create table agenda (id INT PRIMARY KEY, name VARCHAR(100), telefone VARCHAR(100))");
				
				}catch(Exception se1){
				
					System.out.println("Erro ao criar a tabela.");
					se.printStackTrace();
					System.exit(1);				
				
				}
				
			}
	 
	 }
	 
	public ResultSet selectQuery(String query) throws Exception{		
		  
		  return stmt.executeQuery(query);
		
	}
	 
	public boolean insertUpdateDeleteQuery(String query) throws Exception{		
		  
		  return stmt.execute(query) ;
		
	}
	
	public int geraChaveAgenda(){
	
		try{
			
			ResultSet rs = this.selectQuery("select max(id) as id from agenda");
			rs.next();
			int idmax = rs.getInt("id");
			return idmax + 1;
			
		} catch (Exception se) {
				
			System.out.println("Erro ao gerar o id.");
			se.printStackTrace();
			System.exit(1);
			
		}
			
		return 0;
		
	} 
	 
	public void fechaConexao() throws SQLException{
		
		c.close();
		
	}
	 
}

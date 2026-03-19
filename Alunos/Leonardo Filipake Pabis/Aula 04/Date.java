
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.HashMap;


public class Date {
    static HashMap<LocalDate, Double> valoresDiaEMes = new HashMap<>();
    
    static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT);

    public static LocalDate DataValida(String dataString){
        try {
            return LocalDate.parse(dataString+"/2026", dateFormatter);
        } catch (Exception e) {
            return null;
        }
    }

    public static void EscolherDia(double venda_total){
        /*if (venda_total == 0){
            System.out.println("Você ainda não fez nenhuma venda hoje");
            Main.VoltarMenu();
        }*/
        System.out.println("Suas vendas totais: "+venda_total);
        while (true) {
            System.out.println("Escolha o dia para alocar as vendas totais: (dd/mm)");
            String data = Main.scan.nextLine().trim();
            LocalDate dataValida = DataValida(data);
            if (dataValida != null){
            System.out.printf("Alocado %.2f para o dia %s\n", venda_total, data);
            valoresDiaEMes.merge(dataValida, venda_total, Double::sum);
            
            Main.valor_de_venda_total = 0;
            break;
            }else{
            System.out.println("Tente novamente com uma data válida\n");
        }
       
        
        }
        Main.VoltarMenu();
    }

    public static void BuscarVendaDia(){
        DateTimeFormatter formatar = DateTimeFormatter.ofPattern("dd/MM");
        System.out.println("Digite a data para ver seu total de vendas (dd/mm)");
        String data = Main.scan.nextLine().trim();
        LocalDate dataValida = DataValida(data);
        if (dataValida != null){
            valoresDiaEMes.forEach((chave, valor) -> {
            if (dataValida.equals(chave)){
                System.out.println("data " + chave.format(formatar) + " | Valor " + valor+"\n");
            }else{
                System.out.println("Não tem nada nessa data");
                System.out.println(dataValida);
                System.out.println(valoresDiaEMes.keySet());
                Main.VoltarMenu();
            }
            
            });
        }
        
        Main.VoltarMenu();
        }
    }

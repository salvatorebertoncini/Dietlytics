package Diet;
import Dietlytics.Dietlytics;

public class ComposizioneDieta {
	
	public static String composizioneDieta(int kcal, int typediet, int allergia){
		
		float x=(float)kcal/(float)1000;
		System.out.println("kcal: "+kcal+" typediet: "+typediet+" allergia: "+allergia+" coeff: "+x+"\n");
		String Dieta="DIETA PER: "+Dietlytics.user.getWelcome()+"\n", Colazione="COLAZIONE:\n", PrimoSpuntino="PRIMOSPUNTINO:\n", Pranzo="PRANZO:\n", SecondoSpuntino="SECONDOSPUNTINO:\n", Cena="CENA:\n";
		
		switch(allergia){
			//NESSUNA ALLERGIA
			case 1: {
				switch(typediet)
				{
					//NORMALE
					case 1: Colazione+="Latte "+200*x+"gr \nmarmellata prugne "+20*x+"gr o altre marmellate"
							+70*x+"gr \nFetta di Torta o Crostata "+30*x+"gr o Fette Biscottate Integrali "+20*x+"gr o "
							+ "cereali "+25*x+"gr \nCaffè o Tè FACOLTATIVI\n"; 
							PrimoSpuntino+="Yogurt "+130*x+"gr \nAlbicocche "+100*x+"gr o banana "+20*x+"gr\n";
							Pranzo +="Pasta "+100*x+"gr \nolio "+5+"gr \nParmigiano "+100*x+"gr o Bresaola "+200+"gr \nTonno "+50*x+"gr o Vitello "+60*x+"gr o Pollo "+80*x+"gr \nSpinaci "+200*x+"gr o Broccoli "+200*x+"gr\n";
							SecondoSpuntino+="Mela  "+200*x+"gr o Banana "+70*x+"gr\n";
							Cena+="Pane "+50*x+"gr \nPomodoro o Insalata "+100*x+"gr \nLegumi "+100*x+"gr \nolio "+5+"gr\n";
							Dieta+=Colazione+PrimoSpuntino+Pranzo+SecondoSpuntino+Cena;
					break;
					//VEGETARIANA
					case 2: Colazione+="Latte Soia"+300*x+"gr \nmarmellata prugne "+20*x+"gr o altre marmellate"
							+70*x+"gr \nFetta di Torta o Crostata "+30*x+"gr o Fette Biscottate Integrali "+20*x+"gr o "
							+ "cereali "+25*x+"gr \nCaffè o Tè FACOLTATIVI\n"; 
							PrimoSpuntino+="Yogurt "+130*x+"gr \nAlbicocche "+100*x+"gr o banana "+20*x+"gr\n";
							Pranzo +="Pasta "+100*x+"gr \nolio "+5+"gr \nParmigiano "+100*x+"gr \nLegumi "+50*x+"gr \nSpinaci "+200*x+"gr o Broccoli "+200*x+"gr\n";
							SecondoSpuntino+="Mela  "+200*x+"gr o Banana "+70*x+"gr\n";
							Cena+="Pane "+50*x+"gr \nPomodoro  o  Insalata "+100*x+"gr \nLegumi "+100*x+"gr \nolio "+5+"gr\n";
							Dieta+=Colazione+PrimoSpuntino+Pranzo+SecondoSpuntino+Cena;
							break;
					//VEGANA		
					case 3: Colazione+="Latte Soia"+300*x+"gr \n marmellata prugne "+20*x+"gr  o  altre marmellate"
							+70*x+"gr \n Fetta di Torta o Crostata "+30*x+"gr o Fette Biscottate Integrali "+20*x+"gr o "
							+ "cereali "+25*x+"gr\n"; 
							PrimoSpuntino+="Yogurt "+130*x+"gr \nAlbicocche "+100*x+"gr o banana "+20*x+"gr\n";
							Pranzo +="Pasta "+100*x+"gr \nolio "+5+"gr \nParmigiano "+100*x+"gr o Bresaola "+200+"gr \nTonno "+50*x+"gr o Vitello "+60*x+"gr o Pollo "+80*x+"gr \nSpinaci "+200*x+"gr o Broccoli "+200*x+"gr\n";
							SecondoSpuntino+="Mela  "+200*x+"gr o Banana "+70*x+"gr\n";
							Cena+="Pane "+50*x+"gr \nPomodoro  o  Insalata "+100*x+"gr \nLegumi "+100*x+"gr \nolio "+5+"gr\n";
							Dieta+=Colazione+PrimoSpuntino+Pranzo+SecondoSpuntino+Cena;
							break;
					//IPERPROTEICA		
					case 4: Colazione+="Albume di uovo "+200*x+"gr \n Pane Integrale "+50*x+"gr\n"; 
							PrimoSpuntino+="Yogurt "+130*x+"gr \nAlbicocche "+100*x+"gr o banana "+20*x+"gr\n";
							Pranzo +="Pasta "+100*x+"gr \nolio "+5+"gr \nParmigiano "+100*x+"gr o Bresaola "+200+"gr \nTonno "+50*x+"gr o Vitello "+60*x+"gr o Pollo "+80*x+"gr \nSpinaci "+200*x+"gr o Broccoli "+200*x+"gr\n";
							SecondoSpuntino+="Mela  "+200*x+"gr o Banana "+70*x+"gr\n";
							Cena+="Pane "+50*x+"gr \nPomodoro  o  Insalata "+100*x+"gr \nLegumi "+100*x+"gr \nolio "+5+"gr\n";
							Dieta+=Colazione+PrimoSpuntino+Pranzo+SecondoSpuntino+Cena;
							break;
					//DIMAGRANTE		
					case 5: Colazione+="Latte "+(200-25)*x+"gr \n marmellata prugne "+(20-25)*x+"gr  o  altre marmellate"
							+(70-25)*x+"gr \n Fetta di Torta o Crostata "+(30-25)*x+"gr o Fette Biscottate Integrali "+(20-25)*x+"gr o "
							+ "cereali "+x+"gr \n Caffè o Tè FACOLTATIVI\n"; 
							PrimoSpuntino+="Yogurt "+130*x+"gr \nAlbicocche "+100*x+"gr o banana "+20*x+"gr\n";
							Pranzo +="Pasta "+100*x+"gr \nolio "+5+"gr \nParmigiano "+100*x+"gr o Bresaola "+200+"gr \nTonno "+50*x+"gr o Vitello "+60*x+"gr o Pollo "+80*x+"gr \nSpinaci "+200*x+"gr o Broccoli "+200*x+"gr\n";
							SecondoSpuntino+="Mela  "+200*x+"gr o Banana "+70*x+"gr\n";
							Cena+="Pane "+50*x+"gr \nPomodoro  o  Insalata "+100*x+"gr \nLegumi "+100*x+"gr \nolio "+5+"gr\n";
							Dieta+=Colazione+PrimoSpuntino+Pranzo+SecondoSpuntino+Cena;
							break;
				}
			} 
			break;
			//CELIACHIA SENZA GLUTINE
			case 2: 
			{
				switch(typediet)
				{
					//NORMALE 
					case 1: Colazione+="Latte "+200*x+"gr \n marmellata prugne "+20*x+"gr  o  altre marmellate "
							+70*x+"gr \n Fiocchi di Mais"+25*x+"gr \n Caffè o Tè FACOLTATIVI\n"; 
							PrimoSpuntino+="Yogurt "+130*x+"gr \nAlbicocche "+100*x+"gr o banana "+20*x+"gr\n";
							Pranzo +="Pasta "+100*x+"gr \nolio "+5+"gr \nParmigiano "+100*x+"gr o Bresaola "+200+"gr \nTonno "+50*x+"gr o Vitello "+60*x+"gr o Pollo "+80*x+"gr \nSpinaci "+200*x+"gr o Broccoli "+200*x+"gr\n";
							SecondoSpuntino+="Mela  "+200*x+"gr o Banana "+70*x+"gr\n";
							Cena+="Pane "+50*x+"gr \nPomodoro  o  Insalata "+100*x+"gr \nLegumi "+100*x+"gr \nolio "+5+"gr\n";
							Dieta+=Colazione+PrimoSpuntino+Pranzo+SecondoSpuntino+Cena;
							break;
					//VEGETARIANA
					case 2: Colazione+="Latte Soia"+300*x+"gr \n marmellata prugne "+20*x+"gr  o  altre marmellate "
							+70*x+"gr \n Fiocchi di Mais"+25*x+"gr \n Caffè o Tè FACOLTATIVI\n"; 
							PrimoSpuntino+="Yogurt "+130*x+"gr \nAlbicocche "+100*x+"gr o banana "+20*x+"gr\n";
							Pranzo +="Pasta "+100*x+"gr \nolio "+5+"gr \nParmigiano "+100*x+"gr o Bresaola "+200+"gr \nTonno "+50*x+"gr o Vitello "+60*x+"gr o Pollo "+80*x+"gr \nSpinaci "+200*x+"gr o Broccoli "+200*x+"gr\n";
							SecondoSpuntino+="Mela  "+200*x+"gr o Banana "+70*x+"gr\n";
							Cena+="Pane "+50*x+"gr \nPomodoro  o  Insalata "+100*x+"gr \nLegumi "+100*x+"gr \nolio "+5+"gr\n";
							Dieta+=Colazione+PrimoSpuntino+Pranzo+SecondoSpuntino+Cena;
							break;
					//VEGANA
					case 3: Colazione+="Latte Soia"+300*x+"gr \n marmellata prugne "+20*x+"gr  o  altre marmellate "
							+70*x+"gr \n Fiocchi di Mais"+25*x+"gr \n Caffè o Tè FACOLTATIVI"; 
							PrimoSpuntino+="Yogurt "+130*x+"gr \nAlbicocche "+100*x+"gr o banana "+20*x+"gr\n";
							Pranzo +="Pasta "+100*x+"gr \nolio "+5+"gr \nParmigiano "+100*x+"gr o Bresaola "+200+"gr \nTonno "+50*x+"gr o Vitello "+60*x+"gr o Pollo "+80*x+"gr \nSpinaci "+200*x+"gr o Broccoli "+200*x+"gr\n";
							SecondoSpuntino+="Mela  "+200*x+"gr o Banana "+70*x+"gr\n";
							Cena+="Pane "+50*x+"gr \nPomodoro  o  Insalata "+100*x+"gr \nLegumi "+100*x+"gr \nolio "+5+"gr\n";
							Dieta+=Colazione+PrimoSpuntino+Pranzo+SecondoSpuntino+Cena;
							break;
					//IPERPROTEICA
					case 4: Colazione+="Albume di uovo "+200*x+"gr \n Fiocchi di Mais "+40*x+"gr"; 
							PrimoSpuntino+="Yogurt "+130*x+"gr \nAlbicocche "+100*x+"gr o banana "+20*x+"gr\n";
							Pranzo +="Pasta "+100*x+"gr \nolio "+5+"gr \nParmigiano "+100*x+"gr o Bresaola "+200+"gr \nTonno "+50*x+"gr o Vitello "+60*x+"gr o Pollo "+80*x+"gr \nSpinaci "+200*x+"gr o Broccoli "+200*x+"gr\n";
							SecondoSpuntino+="Mela  "+200*x+"gr o Banana "+70*x+"gr\n";
							Cena+="Pane "+50*x+"gr \nPomodoro  o  Insalata "+100*x+"gr \nLegumi "+100*x+"gr \nolio "+5+"gr\n";
							Dieta+=Colazione+PrimoSpuntino+Pranzo+SecondoSpuntino+Cena;
							break;
					//DIMAGRANTE
					case 5: Colazione+="Latte "+(200-25)*x+"gr \n marmellata prugne "+(20-25)*x+"gr  o  altre marmellate "
							+(70-25)*x+"gr \n Fiocchi di Mais"+25*x+"gr \n Caffè o Tè FACOLTATIVI\n"; 
							PrimoSpuntino+="Yogurt "+130*x+"gr \nAlbicocche "+100*x+"gr o banana "+20*x+"gr\n";
							Pranzo +="Pasta "+100*x+"gr \nolio "+5+"gr \nParmigiano "+100*x+"gr o Bresaola "+200+"gr \nTonno "+50*x+"gr o Vitello "+60*x+"gr o Pollo "+80*x+"gr \nSpinaci "+200*x+"gr o Broccoli "+200*x+"gr\n";
							SecondoSpuntino+="Mela  "+200*x+"gr o Banana "+70*x+"gr\n";
							Cena+="Pane "+50*x+"gr \nPomodoro  o  Insalata "+100*x+"gr \nLegumi "+100*x+"gr \nolio "+5+"gr\n";
							Dieta+=Colazione+PrimoSpuntino+Pranzo+SecondoSpuntino+Cena;
							break;	
				}
			}
			break;
			//NICHEL
			case 3:
			{
				switch(typediet)
				{
					//NORMALE 
					case 1: Colazione+="Latte "+200*x+"gr \n marmellata prugne "+20*x+"gr  o  altre marmellate "
							+70*x+"gr \n Fiocchi di Riso"+25*x+"gr \n Caffè o Tè FACOLTATIVI\n"; 
							PrimoSpuntino+="Yogurt "+130*x+"gr \nAlbicocche "+100*x+"gr o banana "+20*x+"gr\n";
							Pranzo +="Pasta "+100*x+"gr \nolio "+5+"gr \nParmigiano "+100*x+"gr o Bresaola "+200+"gr \nTonno "+50*x+"gr o Vitello "+60*x+"gr o Pollo "+80*x+"gr \nSpinaci "+200*x+"gr o Broccoli "+200*x+"gr\n";
							SecondoSpuntino+="Mela  "+200*x+"gr o Banana "+70*x+"gr\n";
							Cena+="Pane "+50*x+"gr \nPomodoro  o  Insalata "+100*x+"gr \nLegumi "+100*x+"gr \nolio "+5+"gr\n";
							Dieta+=Colazione+PrimoSpuntino+Pranzo+SecondoSpuntino+Cena;
							break;
					//VEGETARIANA	
					case 2: Colazione+="Latte Soia"+300*x+"gr \n marmellata prugne "+20*x+"gr  o  altre marmellate "
							+70*x+"gr \n Fiocchi di Riso"+25*x+"gr \n Caffè o Tè FACOLTATIVI\n"; 
							PrimoSpuntino+="Yogurt "+130*x+"gr \nAlbicocche "+100*x+"gr o banana "+20*x+"gr\n";
							Pranzo +="Pasta "+100*x+"gr \nolio "+5+"gr \nParmigiano "+100*x+"gr o Bresaola "+200+"gr \nTonno "+50*x+"gr o Vitello "+60*x+"gr o Pollo "+80*x+"gr \nSpinaci "+200*x+"gr o Broccoli "+200*x+"gr\n";
							SecondoSpuntino+="Mela  "+200*x+"gr o Banana "+70*x+"gr\n";
							Cena+="Pane "+50*x+"gr \nPomodoro  o  Insalata "+100*x+"gr \nLegumi "+100*x+"gr \nolio "+5+"gr\n";
							Dieta+=Colazione+PrimoSpuntino+Pranzo+SecondoSpuntino+Cena;
							break;
					//VEGANA
					case 3: Colazione+="Latte Soia"+300*x+"gr \n marmellata prugne "+20*x+"gr  o  altre marmellate "
							+70*x+"gr \n Fiocchi di Riso"+25*x+"gr \n Caffè o Tè FACOLTATIVI\n"; 
							PrimoSpuntino+="Yogurt "+130*x+"gr \nAlbicocche "+100*x+"gr o banana "+20*x+"gr\n";
							Pranzo +="Pasta "+100*x+"gr \nolio "+5+"gr \nParmigiano "+100*x+"gr o Bresaola "+200+"gr \nTonno "+50*x+"gr o Vitello "+60*x+"gr o Pollo "+80*x+"gr \nSpinaci "+200*x+"gr o Broccoli "+200*x+"gr\n";
							SecondoSpuntino+="Mela  "+200*x+"gr o Banana "+70*x+"gr\n";
							Cena+="Pane "+50*x+"gr \nPomodoro  o  Insalata "+100*x+"gr \nLegumi "+100*x+"gr \nolio "+5+"gr\n";
							Dieta+=Colazione+PrimoSpuntino+Pranzo+SecondoSpuntino+Cena;
							break;
					//IPERPROTEICA
					case 4: Colazione+="Albume di uovo "+200*x+"gr \n Fiocchi di Riso "+40*x+"gr\n"; 
							PrimoSpuntino+="Yogurt "+130*x+"gr \nAlbicocche "+100*x+"gr o banana "+20*x+"gr\n";
							Pranzo +="Pasta "+100*x+"gr \nolio "+5+"gr \nParmigiano "+100*x+"gr o Bresaola "+200+"gr \nTonno "+50*x+"gr o Vitello "+60*x+"gr o Pollo "+80*x+"gr \nSpinaci "+200*x+"gr o Broccoli "+200*x+"gr\n";
							SecondoSpuntino+="Mela  "+200*x+"gr o Banana "+70*x+"gr\n";
							Cena+="Pane "+50*x+"gr \nPomodoro  o  Insalata "+100*x+"gr \nLegumi "+100*x+"gr \nolio "+5+"gr\n";
							Dieta+=Colazione+PrimoSpuntino+Pranzo+SecondoSpuntino+Cena;
							break;
					//DIMAGRANTE
					case 5: Colazione+="Latte "+(200-25)*x+"gr \n marmellata prugne "+(20-25)*x+"gr  o  altre marmellate "
							+(70-25)*x+"gr \n Fiocchi di Riso"+25*x+"gr \n Caffè o Tè FACOLTATIVI\n"; 
							PrimoSpuntino+="Yogurt "+130*x+"gr \nAlbicocche "+100*x+"gr o banana "+20*x+"gr\n";
							Pranzo +="Pasta "+100*x+"gr \nolio "+5+"gr \nParmigiano "+100*x+"gr o Bresaola "+200+"gr \nTonno "+50*x+"gr o Vitello "+60*x+"gr o Pollo "+80*x+"gr \nSpinaci "+200*x+"gr o Broccoli "+200*x+"gr\n";
							SecondoSpuntino+="Mela  "+200*x+"gr o Banana "+70*x+"gr\n";
							Cena+="Pane "+50*x+"gr \nPomodoro  o  Insalata "+100*x+"gr \nLegumi "+100*x+"gr \nolio "+5+"gr\n";
							Dieta=Colazione+PrimoSpuntino+Pranzo+SecondoSpuntino+Cena;
							break;
				}
			}
			break;
			//DIABETE
			case 4:
			{
				switch(typediet)
				{
					//NORMALE 	
					case 1: Colazione+="Latte "+200*x+"gr \n mele "+60*x+"gr  o  ciliegie "+70*x+"gr \n "
							+ "Fiocchi di Mais "+25*x+"gr \n Caffè o Tè FACOLTATIVI\n"; 
							PrimoSpuntino+="Yogurt "+130*x+"gr \nAlbicocche "+100*x+"gr o banana "+20*x+"gr\n";
							Pranzo +="Pasta "+100*x+"gr \nolio "+5+"gr \nParmigiano "+100*x+"gr o Bresaola "+200+"gr \nTonno "+50*x+"gr o Vitello "+60*x+"gr o Pollo "+80*x+"gr \nSpinaci "+200*x+"gr o Broccoli "+200*x+"gr\n";
							SecondoSpuntino+="Mela  "+200*x+"gr o Banana "+70*x+"gr\n";
							Cena+="Pane "+50*x+"gr \nPomodoro  o  Insalata "+100*x+"gr \nLegumi "+100*x+"gr \nolio "+5+"gr\n";
							Dieta+=Colazione+PrimoSpuntino+Pranzo+SecondoSpuntino+Cena;
							break;
					//VEGETARIANA
					case 2: Colazione+="Latte Soia"+300*x+"gr \n mele "+60*x+"gr  o  ciliegie "+70*x+"gr \n "
							+ "Fiocchi di Mais "+25*x+"gr \n Caffè o Tè FACOLTATIVI\n"; 
							PrimoSpuntino+="Yogurt "+130*x+"gr \nAlbicocche "+100*x+"gr o banana "+20*x+"gr\n";
							Pranzo +="Pasta "+100*x+"gr \nolio "+5+"gr \nParmigiano "+100*x+"gr o Bresaola "+200+"gr \nTonno "+50*x+"gr o Vitello "+60*x+"gr o Pollo "+80*x+"gr \nSpinaci "+200*x+"gr o Broccoli "+200*x+"gr\n";
							SecondoSpuntino+="Mela  "+200*x+"gr o Banana "+70*x+"gr\n";
							Cena+="Pane "+50*x+"gr \nPomodoro  o  Insalata "+100*x+"gr \nLegumi "+100*x+"gr \nolio "+5+"gr\n";
							Dieta+=Colazione+PrimoSpuntino+Pranzo+SecondoSpuntino+Cena;
							break;
					//VEGANA
					case 3: Colazione+="Latte Soia "+300*x+"gr \n mele "+60*x+"gr  o  ciliegie "+70*x+"gr \n "
							+ "Fiocchi di Mais "+25*x+"gr \n Caffè o Tè FACOLTATIVI\n"; 
							PrimoSpuntino+="Yogurt "+130*x+"gr \nAlbicocche "+100*x+"gr o banana "+20*x+"gr\n";
							Pranzo +="Pasta "+100*x+"gr \nolio "+5+"gr \nParmigiano "+100*x+"gr o Bresaola "+200+"gr \nTonno "+50*x+"gr o Vitello "+60*x+"gr o Pollo "+80*x+"gr \nSpinaci "+200*x+"gr o Broccoli "+200*x+"gr\n";
							SecondoSpuntino+="Mela  "+200*x+"gr o Banana "+70*x+"gr\n";
							Cena+="Pane "+50*x+"gr \nPomodoro  o  Insalata "+100*x+"gr \nLegumi "+100*x+"gr \nolio "+5+"gr\n";
							Dieta+=Colazione+PrimoSpuntino+Pranzo+SecondoSpuntino+Cena;
							break;
					//IPERPROTEICA
					case 4: Colazione+="Albume di uovo "+200*x+"gr \n Fiocchi di Mais "+40*x+"gr\n"; 
							PrimoSpuntino+="Yogurt "+130*x+"gr \nAlbicocche "+100*x+"gr o banana "+20*x+"gr\n";
							Pranzo +="Pasta "+100*x+"gr \nolio "+5+"gr \nParmigiano "+100*x+"gr o Bresaola "+200+"gr \nTonno "+50*x+"gr o Vitello "+60*x+"gr o Pollo "+80*x+"gr \nSpinaci "+200*x+"gr o Broccoli "+200*x+"gr\n";
							SecondoSpuntino+="Mela  "+200*x+"gr o Banana "+70*x+"gr\n";
							Cena+="Pane "+50*x+"gr \nPomodoro  o  Insalata "+100*x+"gr \nLegumi "+100*x+"gr \nolio "+5+"gr\n";
							Dieta+=Colazione+PrimoSpuntino+Pranzo+SecondoSpuntino+Cena;
							break;
					//DIMAGRANTE
					case 5: Colazione+="Latte "+(200-25)*x+"gr \n mele "+(60-25)*x+"gr  o  ciliegie"
							+(70-25)*x+"gr \n Fiocchi di Mais "+25*x+"gr \n Caffè o Tè FACOLTATIVI\n"; 
							PrimoSpuntino+="Yogurt "+130*x+"gr \nAlbicocche "+100*x+"gr o banana "+20*x+"gr\n";
							Pranzo +="Pasta "+100*x+"gr \nolio "+5+"gr \nParmigiano "+100*x+"gr o Bresaola "+200+"gr \nTonno "+50*x+"gr o Vitello "+60*x+"gr o Pollo "+80*x+"gr \nSpinaci "+200*x+"gr o Broccoli "+200*x+"gr\n";
							SecondoSpuntino+="Mela  "+200*x+"gr o Banana "+70*x+"gr\n";
							Cena+="Pane "+50*x+"gr \nPomodoro  o  Insalata "+100*x+"gr \nLegumi "+100*x+"gr \nolio "+5+"gr\n";
							Dieta+=Colazione+PrimoSpuntino+Pranzo+SecondoSpuntino+Cena;
							break;
				}
			}
			break;
			//LATTOSIO
			case 5:
			{
				switch(typediet)
				{
					//NORMALE 
					case 1: Colazione+="Latte Soia "+300*x+"gr \n mele "+60*x+"gr  o  ciliegie "+70*x+"gr \n "
							+ "Fiocchi di Mais "+25*x+"gr \n Caffè o Tè FACOLTATIVI\n"; 
							PrimoSpuntino+="Yogurt "+130*x+"gr \nAlbicocche "+100*x+"gr o banana "+20*x+"gr\n";
							Pranzo +="Pasta "+100*x+"gr \nolio "+5+"gr \nParmigiano "+100*x+"gr o Bresaola "+200+"gr \nTonno "+50*x+"gr o Vitello "+60*x+"gr o Pollo "+80*x+"gr \nSpinaci "+200*x+"gr o Broccoli "+200*x+"gr\n";
							SecondoSpuntino+="Mela  "+200*x+"gr o Banana "+70*x+"gr\n";
							Cena+="Pane "+50*x+"gr \nPomodoro  o  Insalata "+100*x+"gr \nLegumi "+100*x+"gr \nolio "+5+"gr\n";
							Dieta+=Colazione+PrimoSpuntino+Pranzo+SecondoSpuntino+Cena;
							break;
					//VEGETARIANA
					case 2: Colazione+="Latte Soia "+300*x+"gr \n mele "+60*x+"gr  o  ciliegie "+70*x+"gr \n "
							+ "Fiocchi di Mais "+25*x+"gr \n Caffè o Tè FACOLTATIVI\n"; 
							PrimoSpuntino+="Yogurt "+130*x+"gr \nAlbicocche "+100*x+"gr o banana "+20*x+"gr\n";
							Pranzo +="Pasta "+100*x+"gr \nolio "+5+"gr \nParmigiano "+100*x+"gr o Bresaola "+200+"gr \nTonno "+50*x+"gr o Vitello "+60*x+"gr o Pollo "+80*x+"gr \nSpinaci "+200*x+"gr o Broccoli "+200*x+"gr\n";
							SecondoSpuntino+="Mela  "+200*x+"gr o Banana "+70*x+"gr\n";
							Cena+="Pane "+50*x+"gr \nPomodoro  o  Insalata "+100*x+"gr \nLegumi "+100*x+"gr \nolio "+5+"gr\n";
							Dieta+=Colazione+PrimoSpuntino+Pranzo+SecondoSpuntino+Cena;
							break;
					//VEGANA
					case 3: Colazione+="Latte Soia "+300*x+"gr \n mele "+60*x+"gr  o  ciliegie "+70*x+"gr \n "
							+ "Fiocchi di Mais "+25*x+"gr \n Caffè o Tè FACOLTATIVI\n"; 
							PrimoSpuntino+="Yogurt "+130*x+"gr \nAlbicocche "+100*x+"gr o banana "+20*x+"gr\n";
							Pranzo +="Pasta "+100*x+"gr \nolio "+5+"gr \nParmigiano "+100*x+"gr o Bresaola "+200+"gr \nTonno "+50*x+"gr o Vitello "+60*x+"gr o Pollo "+80*x+"gr \nSpinaci "+200*x+"gr o Broccoli "+200*x+"gr\n";
							SecondoSpuntino+="Mela  "+200*x+"gr o Banana "+70*x+"gr\n";
							Cena+="Pane "+50*x+"gr \nPomodoro  o  Insalata "+100*x+"gr \nLegumi "+100*x+"gr \nolio "+5+"gr\n";
							Dieta+=Colazione+PrimoSpuntino+Pranzo+SecondoSpuntino+Cena;
							break;
					//IPERPROTEICA
					case 4: Colazione+="Albume di uovo "+200*x+"gr \n Fiocchi di Mais "+40*x+"gr\n"; 
							PrimoSpuntino+="Yogurt "+130*x+"gr \nAlbicocche "+100*x+"gr o banana "+20*x+"gr\n";
							Pranzo +="Pasta "+100*x+"gr \nolio "+5+"gr \nParmigiano "+100*x+"gr o Bresaola "+200+"gr \nTonno "+50*x+"gr o Vitello "+60*x+"gr o Pollo "+80*x+"gr \nSpinaci "+200*x+"gr o Broccoli "+200*x+"gr\n";
							SecondoSpuntino+="Mela  "+200*x+"gr o Banana "+70*x+"gr\n";
							Cena+="Pane "+50*x+"gr \nPomodoro  o  Insalata "+100*x+"gr \nLegumi "+100*x+"gr \nolio "+5+"gr\n";
							Dieta+=Colazione+PrimoSpuntino+Pranzo+SecondoSpuntino+Cena;
							break;
					//DIMAGRANTE
					case 5: Colazione+="Latte Soia "+(300-25)*x+"gr \n mele "+(60-25)*x+"gr  o  ciliegie"
							+(70-25)*x+"gr \n Fiocchi di Mais "+25*x+"gr \n Caffè o Tè FACOLTATIVI\n"; 
							PrimoSpuntino+="Yogurt "+130*x+"gr \nAlbicocche "+100*x+"gr o banana "+20*x+"gr\n";
							Pranzo +="Pasta "+100*x+"gr \nolio "+5+"gr \nParmigiano "+100*x+"gr o Bresaola "+200+"gr \nTonno "+50*x+"gr o Vitello "+60*x+"gr o Pollo "+80*x+"gr \nSpinaci "+200*x+"gr o Broccoli "+200*x+"gr\n";
							SecondoSpuntino+="Mela  "+200*x+"gr o Banana "+70*x+"gr\n";
							Cena+="Pane "+50*x+"gr \nPomodoro  o  Insalata "+100*x+"gr \nLegumi "+100*x+"gr \nolio "+5+"gr\n";
							Dieta+=Colazione+PrimoSpuntino+Pranzo+SecondoSpuntino+Cena;
							break;
				}
			}
			break;
	
		}
	
	System.out.println(Dieta);
	return Dieta;
	}

}

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class App {

	public static void main(String[]args) {
		
		String[][] urun_listesi = {{"Domates","6.95"} , {"Salatalik","6.55"} , {"Patlican","8.95"} , {"Havuc","7.95"} , 
								   {"Bezelye","15.90"} , {"Misir","19.95"} , {"Limon","5.95"} , {"Muz","13.90"} ,
								   {"Portakal","7.65"} , {"Cilek","16.95"} , {"Elma","5.95"} , {"Mandalina","5.95"} ,
								   {"Armut","9.95"} , {"Sut","5.50"} , {"Peynir","70"} , {"Yogurt","7.70"} , {"Tereyagi","79.90"} ,
								   {"Yumurta","2.40"} , {"Zeytin","18,95"} , {"Makarna" , "4.90"} , {"Cikolata","2,50"} , 
								   {"Biskuvi","3.25"} , {"Kek","1.25"} , {"Seker" , "0.50"} , {"Su","0.75"}};
	
		System.out.println("Urunlerin Listesi:\n");
		for(int i = 0 ; i < 25 ; i++) {
			if(urun_listesi[i][0].equals("Sut") || urun_listesi[i][0].equals("Yumurta") || urun_listesi[i][0].equals("Makarna") || urun_listesi[i][0].equals("Cikolata") || urun_listesi[i][0].equals("Biskuvi") || urun_listesi[i][0].equals("Kek") || urun_listesi[i][0].equals("Seker") || urun_listesi[i][0].equals("Su")) {
				System.out.println(urun_listesi[i][0] + "(Adet): " + urun_listesi[i][1] + " TL");
			}
			else
				System.out.println(urun_listesi[i][0] + "(Kg): " + urun_listesi[i][1] + " TL");
		}
		ArrayList<String> alinacaklar = new ArrayList<String>();
		ArrayList<Integer> miktar = new ArrayList<Integer>();
		
		System.out.println("\nAlmak istediginiz urunleri once adini sonra miktarini giriniz.");
		String urun = "";
		int mik = 0;
		String s_mik = "";
		boolean kontrol = false;
		boolean m_k = false;
		String ol = "";
		
		Scanner keyboard = new Scanner(System.in);
		
		while(!urun.equals("-1")) {
			ol = "";
			System.out.println("Almak istediginiz urunun adini giriniz (Kg veya adet yazmayiniz sadece urunun adini yaziniz):  Devam etmek istemiyorsaniz cikmak icin -1 giriniz. ");
			urun = keyboard.nextLine();
			
			if(urun.equals("-1")) 
				break;
			
			
			for(int i = 0 ; i < 25 ; i++) {
				if((urun_listesi[i][0].toLowerCase()).equals(urun.toLowerCase())) {
					kontrol = true;
					break;
				}
			}
			
			if(kontrol == false) {
				System.out.println("Girdiginiz urun yanlis. Tekrar giriniz: ");
				continue;
			}
			
			while(m_k == false) {
				System.out.println("Ne kadar almak istiyorsunuz(Sadece tam sayi giriniz)");
				s_mik = keyboard.nextLine();
				if(s_mik.equals("-1")) {
					System.out.println("Bir miktar girdikten sonra sepet olusumunuz tamamlanacaktir.");
					urun = "-1";
					continue;
				}
				
				m_k = true;
				
				if(s_mik.equals("")) {
					m_k = false;
					System.out.println("Yanlis giris yaptiniz.");
					continue;
				}
				
				for(int i = 0 ; i < s_mik.length() ; i++) {
					if((s_mik.charAt(i) < 48 || s_mik.charAt(i) > 57)) {
						m_k = false;
						System.out.println("Yanlis giris yaptiniz.");
						continue;
					}
				}
			}
			
			mik = Integer.parseInt(s_mik);
			
			if(alinacaklar.contains(urun)) {
				miktar.set(alinacaklar.indexOf(urun), miktar.get(alinacaklar.indexOf(urun)) + mik);
			}
			else {
				ol = (urun.charAt(0) + "").toUpperCase() + (urun.substring(1,urun.length())).toLowerCase();
				alinacaklar.add(ol);
				miktar.add(mik);
			}
			
			System.out.println("");
			kontrol = false;
			m_k = false;
		}
		
		System.out.println("Sepetiniz olusturulmustur.");
		
		System.out.println("Bu alisveris icin harcamayi dusundugunuz tutari giriniz");
		String para = "";
		
		while(m_k == false) {
			m_k = true;
			para = keyboard.nextLine();
			for(int i = 0 ; i < para.length() ; i++) {
				if(para.charAt(i) < 48 || para.charAt(i) > 57) {
					m_k = false;
					System.out.println("Yanlis giris yaptiniz. Tekrar giriniz:");
					continue;
				}
			}	
		}
		
		if(para_yeterli_mi(Integer.parseInt(para), alinacaklar, miktar , urun_listesi)) 
			System.out.println("\nAlisverisiniz tamamlanmistir. Bizi tercih ettiginiz icin tesekkurler.");
		
		else 
			System.out.println("\nSepetinizin tutari mevcut paranizi asmistir. Bundan dolayi alisverisiniz tamamlanamamistir.");
		
			System.out.println("\nProgram sonlanmistir.");

	keyboard.close();
		
	}
	
	public static boolean para_yeterli_mi(int mevcut_para, ArrayList<String> alinacaklar, ArrayList<Integer> miktar, String[][] urun_listesi) {
		double tutar = 0;
		String urun = "";
		double fiyat = 0;
				
		for(int i = 0 ; i < alinacaklar.size() ; i++) {
			for(int j = 0 ; j < 25 ; j++) {
				if(urun_listesi[j][0].equals(alinacaklar.get(i))){
					urun = urun_listesi[j][0];
					fiyat = Double.parseDouble(urun_listesi[j][1]);
					break;
				}
			}
			tutar = tutar + fiyat * miktar.get(alinacaklar.indexOf(urun));
		}
		
		DecimalFormat formatter = new DecimalFormat("#.##");
		
		System.out.println("Mevcut Paraniz = " + mevcut_para);
		System.out.println("Sepetinizin Toplam Tutari = " + formatter.format(tutar));
		
		if(tutar > mevcut_para) 
			return false;
		
		return true;
	}
}

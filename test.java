package github;

import java.util.Scanner;

class Food{
	int price;
	int bonusPoint;
	
	Food(int price){
		this.price=price;
		bonusPoint=(int)(price/10);
	}
}
class Cheeseburger extends Food{
	Cheeseburger(){  //조상클래스의 생성자 Food(int price)를 호출
		super(4000);   //치즈버거를 4000원으로 한다
	}
	public String toString() {return "치즈버거";}//Object클래스의 toString()을 오버라이딩
}
class Shrimpburger extends Food{
	Shrimpburger(){  //조상클래스의 생성자 Food(int price)를 호출
		super(4000);   //새우버거를 4000원으로 한다
	}
	public String toString() {return "새우버거";}//Object클래스의 toString()을 오버라이딩
}
class Coke extends Food{
	Coke(){  //조상클래스의 생성자 Food(int price)를 호출
		super(1000);   //콜라를 1000원으로 한다
	}
	public String toString() {return "코카콜라";}//Object클래스의 toString()을 오버라이딩
}
class Buyer{
	Scanner s = new Scanner(System.in);
	int price;
	int bonusPoint;
	int count=0;
	int foodCnt;
	int foodCnt2;
	Food[] cart = new Food[10];
	Integer[] foodAmount = new Integer[10];
    int cartCnt =0;
	int buy(Food selectedFood) {
		if(price==0) { //장바구니 비우면 카트초기화
			cartCnt=0; 
			foodCnt=0;
			foodCnt2=0;
		}
		System.out.println("수량 : ");
		foodAmount[foodCnt++] = s.nextInt(); //수량 입력
		System.out.println(selectedFood+" "+foodAmount[foodCnt2]+"개 장바구니에 담았습니다. ");
		price+=selectedFood.price*foodAmount[foodCnt2]; 
		bonusPoint+=selectedFood.bonusPoint*foodAmount[foodCnt2];
		System.out.println("결제 금액 : "+selectedFood.price*foodAmount[foodCnt2]);
		System.out.println("적립금 : "+selectedFood.bonusPoint*foodAmount[foodCnt2]);
		cart[cartCnt++]=selectedFood;
		foodCnt2++;
		return selectedFood.price; //선택한 상품 단가 리턴
	}
	int sumPrice() { //총금액리턴
		return price;
	}
	int sumBonusPoint() { //총적립금리턴
		return bonusPoint;
	}
	int cartCount() { //카트 채워진 수세기
		for(int i=0;i<cart.length;i++) {
			if (cart[i]==null) break;
			count+=1;
		}
		return count;
	}

	void summary() { //상품요약
		cartCount();
		for(int i =0;i<count;i++) {
			System.out.print(cart[i]+" : ");
			System.out.println(foodAmount[i]+"개");
		}
		count=0;
	}
	int cartCount() { //카트 채워진 수세기
		for(int i=0;i<cart.length;i++) {
			if (cart[i]==null) break;
			count+=1;
		}
		return count;
	}

	void summary() { //상품요약
		cartCount();
		for(int i =0;i<count;i++) {
			System.out.print(cart[i]+" : ");
			System.out.println(foodAmount[i]+"개");
		}
		count=0;
	}
	void removeCart() { //카트비우기
		cartCount();
		for(int i =count-1;i>=0;i--) {
			cart[i]=null;
			foodAmount[i]=0;
		}
		price=0;
		bonusPoint=0;
		count=0;
	}
}
class PaymentSystem{
	private Scanner s;
	Buyer b;
	int giftCard;
	public PaymentSystem() {
		s = new Scanner(System.in);
		b = new Buyer();
	}
	boolean cashPayment(int price, int point) {
		System.out.println("현금 결제 금액을 입력하세요 : ");
		int cash = s.nextInt();
		if(cash >= price) {
			System.out.println("현금 결제가 완료되었습니다.");
			System.out.println("거스름 돈 "+(cash - price)+"원입니다");
			return true;
		}else {
			System.out.println("금액이 부족합니다. 결제가 취소됩니다.");
			return false;
		}
	}
	boolean cardPayment (int price,  int point) {
		System.out.println("총 결제 금액 : "+price);
		System.out.println("총 적립금 : "+point);
		System.out.println("카드 결제를 진행하시겠습니까? (Y/N)");
		String answer = s.next();
		if(answer.equals("Y") || answer.equals("y")){
			return true;
		}else {
			System.out.println("결제가 취소되었습니다.");
			return false;
		}
	}
	boolean giftCardPayment(int giftCard, int price, int point){
		if (price>giftCard) {
			System.out.println("상품권 금액이 부족합니다.");
			return false;
		}else {
			System.out.println("상품권 금액 : "+giftCard);
			System.out.println("총 결제 금액 : "+price);
			System.out.println("총 적립금 : "+point);
			System.out.println("결제를 진행하시겠습니까? (Y/N)");
			String answer = s.next();
			if(answer.equals("Y") || answer.equals("y")){
				return true;
			}else {
				System.out.println("결제가 취소되었습니다.");
				return false;
			}
		}
	}
	int restGiftCard(int amount, int giftCard) { //남은 상품권 금액 리턴
		giftCard-=amount;
		return giftCard;
	}
}

public class test {

	public static void main(String[] args) {
	}
	Scanner s = new Scanner(System.in);
	Buyer b = new Buyer();
	PaymentSystem p = new PaymentSystem();
	Integer[] integer = new Integer[10];
	int menuNum;
	int cheese =0, shrimp = 0, coke = 0; //상품 단가 0으로 초기화
	int unitPrice=0; 
	while(true) {
		System.out.println("------[메뉴]------");
		System.out.println("1. 치즈버거(4000원)");
		System.out.println("2. 새우버거(4000원)");
		System.out.println("3. 코카콜라(1000원)");
		System.out.println("-----------------");
		System.out.println("4. 계산하기 5. 장바구니 보기");
		System.out.println("번호를 선택하세요 : ");
		menuNum = s.nextInt();
		while(true) {
			System.out.println("------[메뉴]------");
			System.out.println("1. 치즈버거(4000원)");
			System.out.println("2. 새우버거(4000원)");
			System.out.println("3. 코카콜라(1000원)");
			System.out.println("-----------------");
			System.out.println("4. 계산하기 5. 장바구니 보기");
			System.out.println("번호를 선택하세요 : ");
			menuNum = s.nextInt();

			if(menuNum==4) {
				break;
			}

			switch(menuNum) {
			case 1:
				cheese = b.buy(new  Cheeseburger());  //단가 4000원 
				break;
			case 2:
				shrimp = b.buy(new  Shrimpburger());  //단가 4000원 
				break;
			case 3:
				coke = b.buy(new  Coke());  //단가 1000원 
				break;
			case 5:
				System.out.println("------[장바구니-최대10개]------");
				System.out.println("상품명");
				b.summary();
				System.out.println("결제 금액 : "+b.sumPrice());
				System.out.println("적립금 : "+b.sumBonusPoint());
				System.out.println("----------------------------");
				System.out.println("1. 주문계속하기 2. 장바구니 비우기");
				int numCart = s.nextInt();
				if(numCart==1) {
					break;
				}else if(numCart==2) {
					b.removeCart();
					System.out.println("장바구니 비우기 완료");
					break;
				}else {
					break;
				}
			default: 
				System.out.println("번호를 잘못입력하였습니다.");
				break;
			}
			if(menuNum==1) {
				integer[unitPrice]=cheese;
				unitPrice++;
			}else if(menuNum==2) {
				integer[unitPrice]=shrimp;
				unitPrice++;
			}else if(menuNum==3) {
				integer[unitPrice]=coke;
				unitPrice++;
			}
		}
		for(int k =0;k<b.count;k++) {
			System.out.println(integer[k]);
		}
		System.out.println("상품명");
		b.summary();
		System.out.println("총 금액은 "+b.sumPrice()+"원입니다.");	
		int total=b.sumPrice();
		System.out.println("||결제 방법을 선택하세요||");
		System.out.println("1: 현금         2: 카드");
		System.out.println("3: 기프티콘  4: 포인트(5000원이상시사용가능)");
		System.out.println("5: 결제 취소");
		int method = s.nextInt();
		boolean success = false;
		int rest=0;
		
		if (method==1) { 
			success = p.cashPayment(total, b.sumBonusPoint());
		}else if (method ==2) {
			success = p.cardPayment(total, b.sumBonusPoint());
		}else if(method==3) {
			System.out.println("상품권 금액 입력 : ");
			int giftCard = s.nextInt();
			success = p.giftCardPayment(giftCard, total,b.sumBonusPoint());
			rest = p.restGiftCard(total, giftCard);
		}else if(method==4) {
			success = p.pointPayment(total, b.sumBonusPoint());
		}
		else if(method ==5) {
			System.out.println("결제가 취소되었습니다.");
			return;
		}else if(method==6){
		}else {
		}
			System.out.println("잘못된 결제 방법입니다.");
		}

		if(success) {
			System.out.println("결제가 완료되었습니다. 감사합니다.");
			System.out.println("영수증을 발급하시겠습니까?(Y/N)");
			String answer = s.next();
			if(answer.equals("Y") || answer.equals("y")){
				System.out.println("===================================");
				System.out.println("   품명          단가          수량          금액");
				System.out.println("===================================");
				b.cartCount();
				for(int i =0;i<b.count;i++) {
					System.out.print(b.cart[i]+"     ");    //품명
					System.out.print(integer[i]+"원       ");   //단가
					System.out.print(b.foodAmount[i]+"개"+"         ");   //수량
					System.out.println(integer[i]*b.foodAmount[i]+"원");   //금액
				}

				System.out.println("===================================");
				System.out.println("총 금액 : " +total+"원");
				System.out.println("적립금액 : " + b.sumBonusPoint()+"원");
				if (method==1) { 
					System.out.println("결제방식 : 현금");
					System.out.println("===================================");
				}else if(method==2) {
					System.out.println("결제방식 : 카드");
					System.out.println("===================================");;
				}else if(method==3) {
					System.out.println("결제방식 : 상품권");
					System.out.println("남은 상품권 금액 : "+ rest+"원");
					System.out.println("===================================");
				}else if(method==4) {
					System.out.println("결제방식 : 포인트");
					System.out.println("남은 포인트 : "+b.sumBonusPoint()+"원");
					System.out.println("===================================");
				}else {
					System.out.println("영수증 발급이 취소되었습니다.");
				}
			}
		}
	}
}


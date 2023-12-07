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

public class test {

	public static void main(String[] args) {
	}

}


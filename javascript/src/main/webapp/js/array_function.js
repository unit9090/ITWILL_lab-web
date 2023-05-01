/**
 * array_function.js
 */

document.addEventListener('DOMContentLoaded', function() {
	// 배열 concat() 메서드: 배열에 새로운 원소를 끝에 추가. 원소가 추가된 새로운 배열을 리턴.
	// 배열 push() 메서드: 기존 배열의 끝에 원소를 추가. 리턴값은 없음.
	
	const numbers = [1, 2, 3, 4, 5, 6, 7];
	
	// 1. 배열 numbers의 원소들 중에서 홀수들만 원소로 갖는 배열을 만들고 출력.
	let odds = [];	// 홀수들을 저장할 배열 선언.
	for(let x of numbers) {	// 배열의 원소를 순서대로 반복
		if(x % 2) {	// 2로 나눈 나머지가 있다면(홀수이면)
			odds = odds.concat(x);	// 홀수를 추가한 새로운 배열을 생성.
		}
	}
	console.log(odds);
	
	/* 다른 풀이 */
	odds = numbers.filter((x) => x % 2);
	console.log('다른 풀이: ', odds);
	
	// 2. 배열 numbers의 원소를 제곱한 숫자들을 원소로 갖는 배열을 만들고 출력.
	let multi = [];
	for(let x of numbers) {
		multi = multi.concat(x * x);	// ** 거듭제곱 연산자
	}
	console.log(multi);
	
	multi = numbers.map((x) => x * x);
	console.log(multi);
	
	// 3. 배열 numbers의 원소들 중에서 홀수의 제곱을 원소로 갖는 배열을 만들고 출력.
	let oddMulti = [];
	for(let x of numbers) {
		if(x % 2) {
			oddMulti = oddMulti.concat(x * x);
		}
	}
	console.log(oddMulti);
	
	oddMulti = numbers.filter((x) => x % 2)
					  .map((x) => x * x);
	console.log(oddMulti);
	
	// 함수
	console.log('------------------');
	console.log(odd(numbers));
	console.log(multis(numbers));
	console.log(multis(odd(numbers)));
	
});


function odd(nums) {
	let odd = [];
	for(let x of nums) {
		if(x % 2 == 1) {
			odd = odd.concat(x);
		}
	}	
	return odd;
}

function multis(nums) {
	let multi = [];
	for(let x of nums) {
		multi = multi.concat(x * x);
	}
	return multi;
}
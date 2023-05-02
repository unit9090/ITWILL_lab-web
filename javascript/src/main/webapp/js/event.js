/**
 * event.js
 */

document.addEventListener('DOMContentLoaded', function() {
	const itemInput = document.querySelector('input#itemInput');
	const btnInput = document.querySelector('button#btnInput');
	const itemList = document.querySelector('ul#itemList');
	const itemInput2 = document.querySelector('input#itemInput2');
	const itemList2 = document.querySelector('ul#itemList2');
	
	// 이벤트 처리 3
	const userName = document.querySelector('input#username');
	const age = document.querySelector('input#age');
	const result = document.querySelector('div#result');
	
	btnInput.addEventListener('click', function() {
		// input에 입력된 내용을 읽음.
		const value = itemInput.value;
		// input에 입력된 값으로 li 요소를 만듦.
		const item = `<li>${value}</li>`;
		// li 요소를 ul에 추가
		itemList.innerHTML += item;		
		// input의 값 지움
		itemInput.value = '';
		// input에 포커스를 줌.
		itemInput.focus();
	});
	
	/* 강사님 */
	itemInput2.addEventListener('keydown', function(e) {
		// console.log(e); -> e: KeyboardEvent 객체
		// 모든 이벤트 핸들러 함수(콜백)은 이벤트 객체를 argument로 전달받음.
		if(e.key === 'Enter') {	// 엔터 키가 눌렸을 때
			const item2 = `<li>${value2.value}</li>`;
			itemList2.innerHTML += item2;
			itemInput2.value = '';
			itemInput2.focus();
		}
	});
	
	/*
	
	itemInput2.addEventListener('keyup', function(event) {		
		if(event.keyCode === 13) {
			// input에 입력된 내용을 읽음.
			const value2 = itemInput2.value;
			// input에 입력된 값으로 li 요소를 만듦.
			const item2 = `<li>${value2}</li>`;
			// li 요소를 ul에 추가
			itemList2.innerHTML += item2;		
			// input의 값 지움
			itemInput2.value = '';
			// input에 포커스를 줌.
			itemInput2.focus();
		}
	});
	
	*/
		
	document.addEventListener('change', function(e) {
		if(e.target == userName || e.target == age) {
			if(userName.value.length != 0 && age.value.length != 0) {
				const html = `<b>name:</b> ${userName.value} <b>age:</b> ${age.value}`;
				result.innerHTML = html;
			}
		}
	});
	
	
});
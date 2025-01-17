//package com.example.dto.response;
//
//public class Hz2 {
//
//    document.getElementById('dataForm').addEventListener('submit', function(event) {
//        event.preventDefault();
//        const name = document.getElementById('name').value;
//        const lastName = document.getElementById('lastName').value;
//        const email = document.getElementById('email').value;
//        const password = document.getElementById('password').value;
//        const phone = document.getElementById('phone').value;
//        const role = document.getElementById('role').value;
//        const category = document.getElementById('category').value;
//
//        // Отправка данных на сервер
//        fetch('http://localhost:8081/admin', {
//                method: 'POST',
//                headers: {
//            'Content-Type': 'application/json'
//        },
//        body: JSON.stringify({
//                name: name,
//                lastName: lastName,
//                email: email,
//                password: password,
//                phone: phone,
//                role: role,
//                category: category
//            })
//        })
//        .then(response => response.json())
//        .then(data => {
//                renderData(data);
//        console.log('Успех:', data);
//        alert('Данные успешно отправлены!');
//        })
//        .catch((error) => {
//                console.error('Ошибка:', error);
//        alert('Произошла ошибка при отправке данных.');
//        });
//    });
//




//    function renderData(data) {
//    const container = document.getElementById('data-container');
//        container.innerHTML = ''; // Clear the container
//
//        // Extract each field into its own variable
//    const id = data.id;
//    const name = data.name;
//    const lastName = data.lastName;
//    const email = data.email;
//    const phone = data.phone;
//    const category = data.category;
//
//    const name1 = data.name;
//
//     const listItemId = document.createElement('li');
//        listItemId.textContent = `Id: ${id}`;
//        container.appendChild(listItemId);
//
//    const listItemName = document.createElement('li');
//        listItemName.textContent = `Name: ${name}`;
//        container.appendChild(listItemName);
//
//    const listItemLastName = document.createElement('li');
//        listItemLastName.textContent = `Last Name: ${lastName}`;
//        container.appendChild(listItemLastName);
//
//    const listItemEmail = document.createElement('li');
//        listItemEmail.textContent = `Email: ${email}`;
//        container.appendChild(listItemEmail);
//
//    const listItemPhone = document.createElement('li');
//        listItemPhone.textContent = `Phone: ${phone}`;
//        container.appendChild(listItemPhone);
//
//    const listItemCategory = document.createElement('li');
//        listItemCategory.textContent = `Category: ${category}`;
//        container.appendChild(listItemCategory);
//
//        document.getElementById('name1').value = name1;
//
//    }
//
//}

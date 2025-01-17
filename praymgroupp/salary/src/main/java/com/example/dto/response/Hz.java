//package com.example.dto.response;
//
//public class Hz {
//
//
//    // Function to send GET request and display data
//    async function fetchData() {
//        try {
//            // Replace with your API endpoint
//            const response = await fetch('http://localhost:8081/admin/all_sallary');
//
//            // Check if the response is successful
//            if (!response.ok) {
//                throw new Error('Network response was not ok');
//            }
//
//            // Parse the JSON response
//            const data = await response.json();
//
//            // Clear previous data
//            document.getElementById('data-container').innerHTML = '';
//
//            // Loop through the array of objects and render each one
//            data.forEach((item, index) => {
//                const card = document.createElement('div');
//                card.className = 'data-card';
//
//                // Access nested properties from the 'owner' object
//                const owner = item.owner;
//
//                // Dynamically create and append fields with unique IDs
//                card.innerHTML = `
//                    <input style="width: 20px;" type="text" id="ownerid-${index}" name="ownerid" value="${owner.id}" readonly>
//                    <input style="width: 70px;" type="text" id="name-${index}" name="name" value="${owner.name}" required>
//                    <input style="width: 70px;" type="text" id="lastName-${index}" name="lastName" value="${owner.lastName}" required>
//                    <input style="width: 90px;" type="text" id="category-${index}" name="category" value="${owner.category}" required>
//                    <input style="width: 10px;" type="text" id="id-${index}" name="id" value="${item.id}" readonly>
//                    <input style="width: 70px;" type="text" id="reportingMonth-${index}" name="reportingMonth" value="${item.reportingMonth}" readonly>
//                    <input style="width: 70px;" type="text" id="salary-${index}" name="salary" value="${item.salary}" required>
//                    <input style="width: 70px;" type="text" id="premiya-${index}" name="premiya" value="${item.premiya}" required>
//                    <input style="width: 70px;" type="text" id="fobo-${index}" name="fobo" value="${item.fobo}" required>
//                    <input style="width: 70px;" type="text" id="miratorg-${index}" name="miratorg" value="${item.miratorg}" required>
//                    <input style="width: 70px;" type="text" id="smety-${index}" name="smety" value="${item.smety}" required>
//                    <input style="width: 70px;" type="text" id="lenta-${index}" name="lenta" value="${item.lenta}" required>
//                    <input style="width: 70px;" type="text" id="avans-${index}" name="avans" value="${item.avans}" required>
//                    <input style="width: 70px;" type="text" id="zpPoKarte-${index}" name="zpPoKarte" value="${item.zpPoKarte}" required>
//                    <input style="width: 70px;" type="text" id="rentCar-${index}" name="rentCar" value="${item.rentCar}" required>
//                    <input style="width: 70px;" type="text" id="rentPhone-${index}" name="rentPhone" value="${item.rentPhone}" required>
//                    <input style="width: 70px;" type="text" id="itog-${index}" name="itog" value="${item.itog}" required readonly>
//                    <br />
//                    <button type="button" class="saveButton">Сохранить</button>
//                `;
//
//                // Append the card to the container
//                document.getElementById('data-container').appendChild(card);
//
//                // Add event listener for the "Save" button
//                const saveButton = card.querySelector('.saveButton');
//                saveButton.addEventListener('click', async () => {
//                // Collect updated data from the input fields
//                    const updatedData = {
//                        ownerid: owner.id,
//                        id: item.id,
//                        salary: parseFloat(card.querySelector(`#salary-${index}`).value),
//                premiya: parseFloat(card.querySelector(`#premiya-${index}`).value),
//                fobo: parseFloat(card.querySelector(`#fobo-${index}`).value),
//                miratorg: parseFloat(card.querySelector(`#miratorg-${index}`).value),
//                smety: parseFloat(card.querySelector(`#smety-${index}`).value),
//                lenta: parseFloat(card.querySelector(`#lenta-${index}`).value),
//                avans: parseFloat(card.querySelector(`#avans-${index}`).value),
//                zpPoKarte: parseFloat(card.querySelector(`#zpPoKarte-${index}`).value),
//                rentCar: parseFloat(card.querySelector(`#rentCar-${index}`).value),
//                rentPhone: parseFloat(card.querySelector(`#rentPhone-${index}`).value),
//                    };
//
//                // Debugging: Log the updated data
//                console.log('Updated Data:', updatedData);
//
//                // Send the updated data to the server
//                try {
//                        const response = await fetch(`http://localhost:8081/admin/${owner.id}/salary/${item.id}`, {
//                    method: 'PATCH',
//                            headers: {
//                        'Content-Type': 'application/json',
//                    },
//                    body: JSON.stringify(updatedData),
//                });
//
//                if (!response.ok) {
//                    throw new Error(`Server responded with status: ${response.status} ${response.statusText}`);
//                }
//
//                        const responseData = await response.json();
//                console.log('Успех:', responseData);
//                alert('Данные успешно сохранены!');
//                    } catch (error) {
//                        console.error('Ошибка при сохранении данных:', error);
//                alert('Произошла ошибка при сохранении данных. Пожалуйста, проверьте консоль для деталей.');
//                    }
//            });
//        });
//    } catch (error) {
//        console.error('Error fetching data:', error);
//        alert('Failed to fetch data. Please try again.');
//    }
//}
//
//// Attach the fetchData function to the button click event
//    document.getElementById('fetchButton').addEventListener('click', fetchData);
//}

document.addEventListener("DOMContentLoaded", () => {
    const usersList = document.getElementById("users-list");
    const carsList = document.getElementById("cars-list");
    const createUserForm = document.getElementById("create-user-form");
    const createCarForm = document.getElementById("create-car-form");

    function displayUsers(users) {
        usersList.innerHTML = "";
        users.forEach(user => {
            const li = document.createElement("li");
            li.textContent = `User ID: ${user.id}, Name: ${user.firstName} ${user.lastName}, Email: ${user.email}`;
            usersList.appendChild(li);
        });
    }

    function displayCars(cars) {
        carsList.innerHTML = "";
        cars.forEach(car => {
            const li = document.createElement("li");
            li.textContent = `Car ID: ${car.id}, Model: ${car.model}, Year: ${car.year}`;
            carsList.appendChild(li);
        });
    }

    fetch("/api/users/")
        .then(response => response.json())
        .then(data => displayUsers(data));

    fetch("/api/cars/")
        .then(response => response.json())
        .then(data => displayCars(data));

    createUserForm.addEventListener("submit", event => {
        event.preventDefault();
        const firstName = document.getElementById("first-name").value;
        const lastName = document.getElementById("last-name").value;
        const email = document.getElementById("email").value;
        const user = { firstName, lastName, email };

        fetch("/api/users/", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(user)
        })
        .then(response => response.json())
        .then(data => {
            displayUsers(data);
            createUserForm.reset();
        });
    });

    createCarForm.addEventListener("submit", event => {
        event.preventDefault();
        const year = document.getElementById("car-year").value;
        const licensePlate = document.getElementById("license-plate").value;
        const model = document.getElementById("model").value;
        const color = document.getElementById("color").value;
        const car = { year, licensePlate, model, color };

        fetch("/api/cars/", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(car)
        })
        .then(response => response.json())
        .then(data => {
            displayCars(data);
            createCarForm.reset();
        });
    });
});
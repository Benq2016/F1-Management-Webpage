document.addEventListener('DOMContentLoaded', function () {
    document.getElementById('change-team-name-btn').addEventListener('click', () => {
        const newName = prompt('Enter new team name:');
        if (newName && newName.trim() !== '') {
            const teamId = document.body.getAttribute('data-team-id');
            fetch(`/teams/${teamId}`, {
                method: 'PUT',
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify({name: newName.trim()})
            })
                .then(response => {
                    if (response.ok)
                        location.reload();
                    else
                        alert('Failed to update team name.');
                });
        }
    });

    document.getElementById('add-driver-btn').addEventListener('click', function () {
        const id = document.getElementById('add-driver-id').value;
        const name = document.getElementById('add-driver-name').value;
        const age = document.getElementById('add-driver-age').value;
        const teamId = document.body.getAttribute('data-team-id');
        if (id && name.trim() && age && teamId !== '') {
            fetch('/drivers', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ id: Number(id), name: name.trim(), age: Number(age), teamID: Number(teamId)})
            }).then(() => location.reload());
        } else {
            alert('Please enter id, name and age.');
        }
    });

    document.querySelectorAll('.update-driver-btn').forEach(button => {
        button.addEventListener('click', function () {
            const li = this.parentElement;
            const id = li.getAttribute('data-driver-id');
            const newName = prompt('Enter new driver name:', li.getAttribute('data-driver-name'));
            const newAge = prompt('Enter new driver age:', li.getAttribute('data-driver-age'));

            if (newName && newName.trim() !== '' && newAge && newAge.trim() !== '') {
                fetch(`/drivers/${id}`, {
                    method: 'PUT',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify({
                        name: newName.trim(),
                        age: parseInt(newAge.trim())
                    })
                }).then(() => location.reload());
            }
        });
    });


    document.querySelectorAll('.delete-driver-btn').forEach(button => {
        button.addEventListener('click', function () {
            const li = this.parentElement;
            const id = li.getAttribute('data-driver-id');
            if (confirm(`Delete driver with ID ${id}?`)) {
                fetch(`/drivers/${id}`, {
                    method: 'DELETE',
                    cache: 'no-store',
                    headers: {
                        'Cache-Control': 'no-cache'
                    }
                }).then(() => location.reload());
            }
        });
    });

    document.getElementById('add-mechanic-btn').addEventListener('click', function () {
        const id = document.getElementById('add-mechanic-id').value;
        const name = document.getElementById('add-mechanic-name').value;
        const salary = document.getElementById('add-mechanic-salary').value;
        const teamID = document.body.getAttribute('data-team-id');
        if (id && name.trim() && salary && teamID !== '') {
            fetch('/mechanics', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ id: Number(id), name: name.trim(), salary: Number(salary), teamID: Number(teamID)})
            }).then(() => location.reload());
        } else {
            alert('Please enter id, name and salary.');
        }
    });

    document.querySelectorAll('.update-mechanic-btn').forEach(button => {
        button.addEventListener('click', function () {
            const li = this.parentElement;
            const id = li.getAttribute('data-mechanic-id');
            const newName = prompt('Enter new mechanic name:', li.getAttribute('data-mechanic-name'));
            const newSalary = prompt('Enter new salary:', li.getAttribute('data-mechanic-salary'));
            if (newName && newSalary !== '') {
                fetch(`/mechanics/${id}`, {
                    method: 'PUT',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify({ name: newName, salary: parseInt(newSalary) })
                }).then(() => location.reload());
            }
        });
    });

    document.querySelectorAll('.delete-mechanic-btn').forEach(button => {
        button.addEventListener('click', function () {
            const li = this.parentElement;
            const id = li.getAttribute('data-mechanic-id');
            if (confirm(`Delete mechanic with ID ${id}?`)) {
                fetch(`/mechanics/${id}`, {
                    method: 'DELETE',
                    cache: 'no-store',
                    headers: {
                        'Cache-Control': 'no-cache'
                    }
                }).then(() => location.reload());
            }
        });
    });

    document.getElementById('add-car-btn').addEventListener('click', function () {
        const id = document.getElementById('add-car-id').value;
        const model = document.getElementById('add-car-model').value;
        const driverID = document.getElementById('add-car-driver-id').value;
        const mechanicID = document.getElementById('add-car-mechanic-id').value;
        const teamID = document.body.getAttribute('data-team-id');

        if (id && model.trim() && driverID && mechanicID && teamID !== '') {
            fetch('/cars', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ id: Number(id), model: model.trim(), driverID: Number(driverID), mechanicID: Number(mechanicID), teamID: Number(teamID)})
            }).then(() => location.reload());
        } else {
            alert('Please enter id, car model, driver id and mechanic id.');
        }
    });

    document.querySelectorAll('.update-car-btn').forEach(button => {
        button.addEventListener('click', function () {
            const li = this.parentElement;
            const id = li.getAttribute('data-car-id');
            const newModel = prompt('Enter new car model:', li.getAttribute('data-car-model'));
            if (newModel) {
                fetch(`/cars/${id}`, {
                    method: 'PUT',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify({ model: newModel })
                }).then(() => location.reload());
            }
        });
    });

    document.querySelectorAll('.delete-car-btn').forEach(button => {
        button.addEventListener('click', function () {
            const li = this.parentElement;
            const id = li.getAttribute('data-car-id');
            if (confirm(`Delete car with ID ${id}?`)) {
                fetch(`/cars/${id}`, {
                    method: 'DELETE',
                    cache: 'no-store',
                    headers: {
                        'Cache-Control': 'no-cache'
                    }
                }).then(() => location.reload());
            }
        });
    });

    document.getElementById('add-participation-btn').addEventListener('click', function () {
        const carID = document.getElementById('add-participation-car-id').value;
        const raceID = document.getElementById('add-participation-race-id').value;
        if (carID && raceID !== '') {
            console.log(carID, raceID);
            fetch('/participations', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ carId: Number(carID), raceId: Number(raceID) })
            })
                .then(response => {
                    if (!response.ok) throw new Error("Participation failed");
                    location.reload();
                })
                .catch(err => alert(err.message));
        } else {
            alert('Please enter both Car ID and Race ID.');
        }
    });
});
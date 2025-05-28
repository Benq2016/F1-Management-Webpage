document.addEventListener('DOMContentLoaded', function () {
    document.getElementById('add-race-btn').addEventListener('click', function () {
        const pin = prompt('Enter admin 4-digit PIN to add a race:');
        if (!pin || pin.length !== 4 || !/^\d{4}$/.test(pin)) {
            alert('Invalid PIN format. Must be 4 digits.');
            return;
        }
        fetch(`/races-pin-check?pin=${pin}`, {
            method: 'GET',
            headers: { 'Accept': 'text/plain' }
        })
            .then(response => {
                if (!response.ok) throw new Error('PIN verification failed.');
                return response.text();
            })
            .then(result => {
                if (result !== 'valid') throw new Error('Invalid PIN');

                const id = document.getElementById('add-race-id').value;
                const name = document.getElementById('add-race-name').value;
                const location = document.getElementById('add-race-location').value;
                const dateInput = document.getElementById('add-race-date').value;
                const date = `${dateInput}T00:00:00`;

                if (!id || !name || !location || !dateInput) {
                    alert('Please fill in all race fields.');
                    return;
                }
                fetch('/races', {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify({ id: Number(id), name: name.trim(), location: location.trim(), date: date })
                })
                    .then(res => {
                        if (!res.ok) throw new Error('Failed to add race.');
                        window.location.reload();
                    });
            })
            .catch(err => {
                alert(err.message || 'PIN check failed.');
            });
    });
});

document.addEventListener('DOMContentLoaded', function () {
    document.getElementById('add-team-btn').addEventListener('click', function () {
        const id = document.getElementById('add-team-id').value;
        const name = document.getElementById('add-team-name').value;
        if (id && name) {
            fetch('/teams', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ id: Number(id), name: name.trim() })
            }).then(() => location.reload());
        } else {
            alert('Please enter both ID and name.');
        }
    });

    document.querySelectorAll('.delete-team-btn').forEach(button => {
        button.addEventListener('click', function() {
            const li = this.parentElement;
            const id = li.getAttribute('data-team-id');
            const pin = prompt('Enter the 4-digit PIN code to confirm deletion:');
            if (!pin || pin.length !== 4 || !/^\d{4}$/.test(pin)) {
                alert('Please enter a valid 4-digit PIN code.');
                return;
            }
            fetch(`/password/${id}`, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ pin })
            })
                .then(response => {
                    if (!response.ok) throw new Error('Invalid PIN code.');
                    return response.json();
                })
                .then(data => {
                    if (!data.valid) {
                        alert('Incorrect PIN.');
                        return;
                    }
                    if (confirm(`Delete team with ID ${id}?`)) {
                        fetch(`/teams/${id}`, {
                            method: 'DELETE',
                            cache: 'no-store',
                            headers: { 'Cache-Control': 'no-cache' }
                        }).then(response => {
                            if (response.ok) {
                                fetch(`/password/${id}`, {
                                    method: 'DELETE',
                                    cache: 'no-store',
                                    headers: { 'Cache-Control': 'no-cache' }
                                }).finally(() => location.reload());
                            } else alert('Failed to delete team.');
                        });
                    }
                }).catch(err => alert(err.message));
        });
    });

    document.querySelectorAll('.manage-team-btn').forEach(button => {
        button.addEventListener('click', function() {
            const teamID = this.parentElement.getAttribute('data-team-id');
            const pin = prompt('Enter 4-digit PIN code:');
            if (!pin || pin.length !== 4 || !/^\d{4}$/.test(pin)) {
                alert('Please enter a valid 4-digit PIN code.');
                return;
            }
            window.location.href = `/teams/${teamID}/manage?passcode=${pin}`;
        });
    });
});

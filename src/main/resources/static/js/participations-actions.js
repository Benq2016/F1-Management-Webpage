document.addEventListener('DOMContentLoaded', function () {
    document.getElementById('driverFilter').addEventListener('input', function () {
        const filterText = this.value.toLowerCase();
        document.querySelectorAll('.participation-item').forEach(item => {
            const driverName = item.querySelector('.driver-name').textContent.toLowerCase();
            item.style.display = driverName.includes(filterText) ? '' : 'none';
        });
    });
});
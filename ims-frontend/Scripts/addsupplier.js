function Notify(message, type) {
    const toastLiveExample = document.getElementById('liveToast');
    const toastBody = toastLiveExample.querySelector('.toast-body');
    
    toastBody.innerText = message;
    toastLiveExample.classList.remove('bg-success', 'bg-danger', 'bg-warning', 'bg-info');

    if (type === 'success') {
        toastLiveExample.classList.add('bg-success');
    } else if (type === 'danger') {
        toastLiveExample.classList.add('bg-danger');
    } else if (type === 'warning') {
        toastLiveExample.classList.add('bg-warning');
    }

    const toastBootstrap = new bootstrap.Toast(toastLiveExample);
    toastBootstrap.show();
}

function onNavItemClick(itemId, url) {

    // Your existing code...
    
    if (url) {
        window.location.href = url;
    }
}

function extractCookie() {
    const cookieRow = document.cookie.split('; ').find(row => row.startsWith('cookie=='));
    return cookieRow ? cookieRow.split('==')[1] : '';
}

const godownId = parseInt(JSON.parse(localStorage.getItem('user')).godownId);
const cookie = extractCookie();


// // Initialize DataTable
// $(document).ready(function() {
//     $('#myTable').DataTable();
// });

function handleFormSubmit(event) {
    // Prevent the form from submitting normally
    console.log("hello");

    event.preventDefault();

    // Get the data from the form
    var supplierName = document.querySelector('input[placeholder="Name"]').value;
    var address = document.querySelector('input[placeholder="Address"]').value;
    var contactNumber = document.querySelector('input[placeholder="Contact Number"]').value;


    // Call the Add Supplier API
    fetch('http://localhost:8080/api/createSupplier', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${cookie}`
        },
        body: JSON.stringify({
            supplierName: supplierName,
            address: address,
            contactNumber: contactNumber
        })
    })
    .then(response => response.text())
    .then(data => {
        // Handle the response...
        Notify('Supplier added successfully!', 'success');
        setTimeout(() => {
            window.location.href = 'supplier.html';
        }, 1000);

    })
    .catch(error => {
        console.error('Error:', error);
        Notify('An error occurred while adding the supplier.', 'danger');
    });
}

// function handleFormSubmit(event) {
//     // Prevent the default form submission
//         event.preventDefault();

//     // Get the form element
//     const form = event.target;

//     // Get the form inputs
//     const nameInput = form.querySelector('input[name="name"]');
//     const addressInput = form.querySelector('input[name="address"]');
//     const contactInput = form.querySelector('input[name="contact"]');

//     // Validate the form inputs
//     if (nameInput.value.trim() === '' || addressInput.value.trim() === '' || contactInput.value.trim() === '') {
//         alert('Please fill in all fields');
//         return;
//     }

//     // If all fields are filled, submit the form
//     form.submit();
// }












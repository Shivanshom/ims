// Handle navigation item clicks
function onNavItemClick(itemId, url) {
  if (url) {
    window.location.href = url;
  }
}

// Function to handle form submission and confirmation
function updateStatus() {
  if (confirm("Are you sure you want to save GodownHead details?")) {
    document
      .getElementById("addGodownHead")
      .addEventListener("click", function (e) {
        e.preventDefault(); // Prevent form submission

        // Fetch input values using e.target
        var formData = {
          godownId: e.target.elements.godownId.value,
          godownHeadName: e.target.elements.godownHeadName.value,
          username: e.target.elements.username.value,
          email: e.target.elements.email.value,
          password: e.target.elements.password.value,
          address: e.target.elements.address.value,
          godownHeadNo: e.target.elements.godownHeadNo.value,
        };
        console.log(formData);

        // Send data to backend API using Axios
        axios
          .post("http://localhost:8080/api/register", formData, {
            headers: {
              "Content-Type": "application/json",
            },
          })
          .then((response) => {
            console.log(response.data);
            alert("GodownHead details saved successfully!");
            // You can redirect the user to another page here if needed
          })
          .catch((error) => {
            console.error("Error:", error);
            alert("Username is already taken, Please use another username");
          });
      });
  }
}
document.addEventListener("DOMContentLoaded", () => {
    document.getElementById("addGodownHead").addEventListener("submit", function(e) {
        e.preventDefault(); // Prevent form submission
    
        // Fetch input values using e.target
        var formData = {
            username: e.target.elements.username.value,
            password: e.target.elements.password.value,
            godownHeadName: e.target.elements.godownHeadName.value,
            godownId: e.target.elements.godownId.value,
            email: e.target.elements.email.value,
            address: e.target.elements.address.value, // Corrected line to capture address value
            godownHeadNo: e.target.elements.godownHeadNo.value
        };
        console.log(formData);
    
        // Send data to backend API using Axios
        axios.post('http://localhost:8080/api/register', formData, {
            headers: {
                'Content-Type': 'application/json'
            }
        })
        .then(response => {
            console.log(response.data);
            alert('GodownHead details saved successfully!');
            // You can redirect the user to another page here if needed
        })
        .catch(error => {
            console.error('Error:', error);
            alert('Username is already taken, Please use another username');
        });
    });
});




// const toggleButton = document.getElementById('nav-toggle');
// const navLinks = document.getElementById('nav-links');

// toggleButton.addEventListener('click', () => {
//     navLinks.classList.toggle('active')
// })

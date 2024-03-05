function extractCookie() {
    const cookieRow = document.cookie.split('; ').find(row => row.startsWith('cookie=='));
    return cookieRow ? cookieRow.split('==')[1] : '';
}

function logout(buttonId) {
    document.getElementById(buttonId).addEventListener("click", function(e) {
        e.preventDefault();
        const cookie = extractCookie();
        axios.post('http://localhost:8080/api/logout', null, {
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${cookie}`
            }
        })
        .then(response => {
            if (response.status === 200) {
                localStorage.clear();
                document.cookie = `cookie==${cookie}; max-age=-99999999;`;
                alert('You have been logged out successfully.');
                window.location.href = "login.html";
            } else {
                console.error('Logout error:', response.data);
                alert('Logout failed. Please try again.');
            }
        })
        .catch(error => {
            console.error('Logout error:', error);
            alert('An unexpected error occurred. Please try again later.');
        });
    });
}

document.addEventListener('DOMContentLoaded', function() {
    logout('logout-btn-1');
    // logout('logout-btn-2');
});

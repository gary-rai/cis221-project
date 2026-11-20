
 // Get the login form and error message
const loginForm = document.getElementById("loginForm");
const loginError = document.getElementById("loginError");

// Handle the login form
if (loginForm && loginError) {

loginForm.addEventListener("submit", function(event) {

event.preventDefault();

const email = document.getElementById("email").value.trim();
const password = document.getElementById("password").value;

loginError.textContent = "";

// Check if the fields are empty
if (email === "" || password === "") {

loginError.textContent = "Please enter your email and password.";
return;

}

// Wait for the Java backend
loginError.textContent = "Login service is not connected yet.";

});

}

// Get the dashboard elements
const logFile = document.getElementById("logFile");
const selectedFile = document.getElementById("selectedFile");
const analyzeButton = document.getElementById("analyzeButton");
const uploadMessage = document.getElementById("uploadMessage");

// Handle file selection and analysis
if (logFile && selectedFile && analyzeButton && uploadMessage) {

// Display the selected file name
logFile.addEventListener("change", function() {

uploadMessage.textContent = "";

if (logFile.files.length === 0) {

selectedFile.textContent = "No file selected";
return;

}

const file = logFile.files[0];

// Only allow TXT files
if (!file.name.toLowerCase().endsWith(".txt")) {

selectedFile.textContent = "No file selected";
uploadMessage.textContent = "Please select a TXT log file.";
logFile.value = "";
return;

}

selectedFile.textContent = file.name;

});

// Handle the Analyze button
analyzeButton.addEventListener("click", function() {

uploadMessage.textContent = "";

if (logFile.files.length === 0) {

uploadMessage.textContent = "Please select a log file first.";
return;

}

uploadMessage.textContent = "Analysis service is not connected yet.";

});

}

// Update the dashboard statistics
function updateDashboard(results) {

document.getElementById("totalLogins").textContent = results.totalLogins;
document.getElementById("successfulLogins").textContent = results.successfulLogins;
document.getElementById("failedLogins").textContent = results.failedLogins;

// Update successful and failed login counts
document.getElementById("successCount").textContent = results.successfulLogins;
document.getElementById("failedCount").textContent = results.failedLogins;

// Calculate percentages for the progress bars
let successPercent = 0;
let failedPercent = 0;

if (results.totalLogins > 0) {

successPercent = (results.successfulLogins / results.totalLogins) * 100;
failedPercent = (results.failedLogins / results.totalLogins) * 100;

}

// Update the progress bars
document.getElementById("successBar").style.width = successPercent + "%";
document.getElementById("failedBar").style.width = failedPercent + "%";

}

// Get the login form and error message
const loginForm = document.getElementById("loginForm");
const loginError = document.getElementById("loginError");

// Check if the login form exists
if (loginForm) {

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

// Wait for the Java backend to handle authentication
loginError.textContent = "Login service is not connected yet.";

});

}
// Get the dashboard elements
const logFile = document.getElementById("logFile");
const selectedFile = document.getElementById("selectedFile");
const analyzeButton = document.getElementById("analyzeButton");
const uploadMessage = document.getElementById("uploadMessage");

// Check if the dashboard exists
if (logFile && selectedFile && analyzeButton && uploadMessage) {

// Display the selected file name
logFile.addEventListener("change", function() {

uploadMessage.textContent = "";

if (logFile.files.length === 0) {

selectedFile.textContent = "No file selected";
return;

}

const file = logFile.files[0];

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
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
analyzeButton.addEventListener("click", async function() {

    const file = logFile.files[0];

    if (!file) {
        uploadMessage.textContent = "Please select a TXT file.";
        return;
    }

    const text = await file.text();

    let records = [];
    let successful = 0;
    let failed = 0;

    text.split("\n").forEach(line => {

        if (!line.trim()) return;

        const parts = line.split("|").map(x => x.trim());

        if (parts.length < 4) return;

        const status = parts[3];

        if (status === "SUCCESS") {
            successful++;
        } else if (status === "FAILED") {
            failed++;
        } else {
            return;
        }

        records.push({
            timestamp: parts[0],
            email: parts[1],
            ip: parts[2],
            status: status
        });
    });

    updateDashboard({
        totalLogins: successful + failed,
        successfulLogins: successful,
        failedLogins: failed,
        records: records
    });

    uploadMessage.textContent = "Analysis completed!";
});
}

// Update dashboard statistics and individual login records
function updateDashboard(results) {

// Get dashboard elements
const totalLogins = document.getElementById("totalLogins");
const successfulLogins = document.getElementById("successfulLogins");
const failedLogins = document.getElementById("failedLogins");

const successCount = document.getElementById("successCount");
const failedCount = document.getElementById("failedCount");

const successBar = document.getElementById("successBar");
const failedBar = document.getElementById("failedBar");

const recordsTable = document.getElementById("loginRecords");

// Update the statistics
totalLogins.textContent = results.totalLogins;
successfulLogins.textContent = results.successfulLogins;
failedLogins.textContent = results.failedLogins;

successCount.textContent = results.successfulLogins;
failedCount.textContent = results.failedLogins;


// Calculate percentages
let successPercent = 0;
let failedPercent = 0;

if (results.totalLogins > 0) {

successPercent = (results.successfulLogins / results.totalLogins) * 100;
failedPercent = (results.failedLogins / results.totalLogins) * 100;

}

// Update progress bars
successBar.style.width = successPercent + "%";
failedBar.style.width = failedPercent + "%";


// Display individual login records
recordsTable.innerHTML = "";

if (results.records && results.records.length > 0) {

results.records.forEach(function(record) {

// Create a new table row
const row = document.createElement("tr");

// Create timestamp column
const timestamp = document.createElement("td");
timestamp.textContent = record.timestamp;

// Create email column
const email = document.createElement("td");
email.textContent = record.email;

// Create IP address column
const ip = document.createElement("td");
ip.textContent = record.ip;

// Create login status column
const status = document.createElement("td");
status.textContent = record.status;

// Display successful and failed statuses
if (record.status === "SUCCESS") {

status.style.color = "#35b98b";

} else {

status.style.color = "#f47777";

}

status.style.fontWeight = "bold";

// Add information to the table row
row.appendChild(timestamp);
row.appendChild(email);
row.appendChild(ip);
row.appendChild(status);

// Add row to the records table
recordsTable.appendChild(row);

});

} else {

// Display message if no records exist
const row = document.createElement("tr");
const message = document.createElement("td");

message.colSpan = 4;
message.textContent = "No login records found.";
message.className = "empty-records";

row.appendChild(message);
recordsTable.appendChild(row);

}

}

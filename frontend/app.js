const API = "http://localhost:8080";

// Add student
function addStudent() {
  const student = {
    name: document.getElementById("name").value,
    email: document.getElementById("email").value,
    phone: document.getElementById("phone").value
  };
  // 🔥 Check if updating
  if (window.editId) {
    fetch(API + "/students/" + window.editId, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify(student)
    })
    .then(() => {
      document.getElementById("message").innerText = "Student updated!";
      window.editId = null;
      document.querySelector("button").innerText = "Add Student";
      loadStudents();
    });

  } else {
    // Normal ADD
    fetch(API + "/students", {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify(student)
    })
    .then(() => {
      document.getElementById("message").innerText = "Student added!";
      loadStudents();
      loadDashboard();
    });
  }
  // Clear inputs
  document.getElementById("name").value = "";
  document.getElementById("email").value = "";
  document.getElementById("phone").value = "";
}

// Load students
function loadStudents() {

  document.getElementById("studentList").innerHTML = "<p>Loading students...</p>";

  fetch(API + "/students")
    .then(res => res.json())
    .then(data => {
      const list = document.getElementById("studentList");
      list.innerHTML = "";

      data.forEach(s => {
        const li = document.createElement("li");
        li.className = "list-group-item d-flex justify-content-between align-items-center";

        li.innerHTML = `
          ${s.name} - ${s.email}

          <div>
            <button class="btn btn-warning btn-sm me-2" onclick="editStudent(${s.id}, '${s.name}', '${s.email}', '${s.phone}')">
              Edit
            </button>

            <button class="btn btn-danger btn-sm" onclick="deleteStudent(${s.id})">
              Delete
            </button>
          </div>
        `;

        list.appendChild(li);
      });
    });
}


function deleteStudent(id) {
  // ✅ Confirm before delete
  if (!confirm("Are you sure you want to delete this student?")) {
    return;
  }

  fetch(API + "/students/" + id, {
    method: "DELETE"
  })
  .then(() => {
    loadStudents();
    loadDashboard();

    // ✅ Optional success message
    document.getElementById("message").innerText = "Student deleted successfully!";
  });
}


function editStudent(id, name, email, phone) {
  document.getElementById("name").value = name;
  document.getElementById("email").value = email;
  document.getElementById("phone").value = phone;

  // Store id globally
  window.editId = id;

  // Change button text
  document.querySelector("button").innerText = "Update Student";
}


function loadDashboard() {
  fetch(API + "/dashboard")
    .then(res => res.json())
    .then(data => {
      document.getElementById("totalStudents").innerText = data.totalStudents;
      document.getElementById("vacantRooms").innerText = data.vacantRooms;
      document.getElementById("pendingFees").innerText = data.pendingFees;
    });
}

// Auto load on page open
loadStudents();
loadDashboard();
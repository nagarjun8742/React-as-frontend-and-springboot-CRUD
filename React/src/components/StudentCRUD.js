import React, { useEffect, useState } from "react";
import {
  getStudents,
  addStudent,
  updateStudent,
  deleteStudent,
} from "../services/StudentService";

function StudentCRUD() {
  const [students, setStudents] = useState([]);
  const [form, setForm] = useState({
    name: "",
    email: "",
    course: "",
  });
  const [editId, setEditId] = useState(null);

  useEffect(() => {
    loadStudents();
  }, []);

  const loadStudents = async () => {
    const res = await getStudents();
    setStudents(res.data);
  };

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async () => {
    if (editId) {
      await updateStudent(editId, form);
    } else {
      await addStudent(form);
    }

    setForm({ name: "", email: "", course: "" });
    setEditId(null);
    loadStudents();
  };

  const handleEdit = (stu) => {
    setForm(stu);
    setEditId(stu.id);
  };

  const handleDelete = async (id) => {
    await deleteStudent(id);
    loadStudents();
  };

  return (
    <div style={{ padding: "20px" }}>
      <h2>🎓 Student Management System</h2>

      <h3>Create New Student</h3>
      <input name="name" placeholder="Name" value={form.name} onChange={handleChange} />
      <input name="email" placeholder="Email" value={form.email} onChange={handleChange} />
      <input name="course" placeholder="Course" value={form.course} onChange={handleChange} />
      <button onClick={handleSubmit}>
        {editId ? "Update" : "Create"}
      </button>

      <h3>All Students</h3>
      <table border="1" cellPadding="10">
        <thead>
          <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Course</th>
            <th>Actions</th>
          </tr>
        </thead>

        <tbody>
          {students.map((stu) => (
            <tr key={stu.id}>
              <td>{stu.id}</td>
              <td>{stu.name}</td>
              <td>{stu.email}</td>
              <td>{stu.course}</td>
              <td>
                <button onClick={() => handleEdit(stu)}>Edit</button>
                <button onClick={() => handleDelete(stu.id)}>Delete</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default StudentCRUD;
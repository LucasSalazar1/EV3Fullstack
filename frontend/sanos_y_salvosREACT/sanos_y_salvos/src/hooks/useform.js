import { useState } from "react";
import { useNavigate } from "react-router-dom";

export const useForm = (initialValues) => {
  const [form, setForm] = useState(initialValues);
  const [loading, setLoading] = useState(false);
  const navigate = useNavigate();

  const handleChange = (event) => {
    const { name, value } = event.target;
    setForm({ ...form, [name]: value });
  };

  const handleSubmit = async (event) => {
    event.preventDefault();
    setLoading(true);

    try {
      const usuario = {
        nombre: form.nombre,
        correo: form.email,
        password: form.password,
        rol: "USER"
      };

      const response = await fetch("http://localhost:8081/api/usuarios", {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify(usuario)
      });

      if (!response.ok) {
        throw new Error("Error al registrar usuario");
      }

      alert("Usuario registrado correctamente");
      navigate("/Pag_inicioSesion");

    } catch (error) {
      console.error(error);
      alert("No se pudo registrar el usuario");
    } finally {
      setLoading(false);
    }
  };

  return { form, loading, handleChange, handleSubmit };
};

export default useForm;
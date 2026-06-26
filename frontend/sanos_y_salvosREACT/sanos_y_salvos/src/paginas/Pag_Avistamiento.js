import React, { useState } from "react";
import { useParams } from "react-router-dom";

const Pag_Avistamiento = () => {

    const { id } = useParams();

    const [form, setForm] = useState({
        lugar: "",
        fecha: "",
        comentario: "",
        nombreReportante: "",
        contactoReportante: ""
    });

    const handleChange = (e) => {
        setForm({
            ...form,
            [e.target.name]: e.target.value
        });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {

            const response = await fetch(
                `http://localhost:8081/api/avistamientos/${id}`,
                {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/json"
                    },
                    body: JSON.stringify(form)
                }
            );

            if (!response.ok) {
                throw new Error("Error al registrar avistamiento");
            }

            alert("Avistamiento registrado correctamente");

            window.location.href = "/";

        } catch (error) {
            console.error(error);
            alert("No se pudo registrar el avistamiento");
        }
    };

    return (
        <>
            <section className="header">
                <a href="/" className="button">Volver</a>
                <div className="logo">
                    <h1 className="center">Sanos y salvos</h1>
                </div>
            </section>
            
            <center>
                <h2 className="logo">Reportar avistamiento</h2>
            </center>

            <section className="main">
                <div className="registro-container" style={{ maxWidth: "450px", margin: "auto" }}>
                    <form
                        onSubmit={handleSubmit}
                        style={{
                            display: "flex",
                            flexDirection: "column",
                            gap: 15
                        }}
                    >
                        <input
                            type="text"
                            name="lugar"
                            placeholder="Lugar de avistamiento"
                            onChange={handleChange}
                            required
                        />

                        <input
                            type="date"
                            name="fecha"
                            onChange={handleChange}
                            required
                        />

                        <textarea
                            name="comentario"
                            placeholder="Comentarios adicionales"
                            onChange={handleChange}
                        />

                        <input
                            type="text"
                            name="nombreReportante"
                            placeholder="Tu nombre"
                            onChange={handleChange}
                        />

                        <input
                            type="text"
                            name="contactoReportante"
                            placeholder="Tu teléfono o contacto"
                            onChange={handleChange}
                        />

                        <button type="submit" className="button">
                            Reportar avistamiento
                        </button>
                    </form>
                </div>
            </section>
        </>
    );
};

export default Pag_Avistamiento;
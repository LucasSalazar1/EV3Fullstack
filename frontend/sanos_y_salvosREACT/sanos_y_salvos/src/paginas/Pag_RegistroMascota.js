import React, { useState } from "react";
import { Helmet } from "react-helmet";
import Config from "../Config.json";

const Pag_RegistroMascota = () => {
    const [mascota, setMascota] = useState({
        nombre: "",
        tipoMascota: "",
        descripcion: "",
        ultimaVezVisto: "",
        ubicacion: "",
        imagenUrl: "",
        recompensa: ""
    });

    const handleChange = (e) => {
        setMascota({
            ...mascota,
            [e.target.name]: e.target.value
        });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        const usuarioLogged = JSON.parse(localStorage.getItem("usuario"));

        try {
            const response = await fetch("http://localhost:8081/api/mascotas", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
               body: JSON.stringify({
    nombre: mascota.nombre,
    tipo_Mascota: mascota.tipoMascota,
    descripcion: mascota.descripcion,
    UVV: mascota.ultimaVezVisto,
    region_usuario: mascota.ubicacion,
    imagenUrl: mascota.imagenUrl,
    recompensa: Number(mascota.recompensa),
    usuario: usuarioLogged ? { id: usuarioLogged.id } : null
})
            });

            if (!response.ok) {
                throw new Error("Error al registrar mascota");
            }

            alert("Mascota registrada correctamente");
            window.location.href = "/";

        } catch (error) {
            console.error(error);
            alert("No se pudo registrar la mascota");
        }
    };

    return (
        <>
            <Helmet>
                <title>{"Registrar Mascota | " + Config.SITE_TITLE}</title>
            </Helmet>

            <section className="header">
                <a href="/" className="button">Volver</a>
                <div className="logo">
                    <h1 className="center">Sanos y Salvos</h1>
                </div>
            </section>

            <center>
                <h2 className="logo">Registrar mascota</h2>
            </center>

            <section className="main">
                <div className="registro-container" style={{ maxWidth: "450px", margin: "auto" }}>
                    <form
                        className="form-registro"
                        style={{ display: "flex", flexDirection: "column", gap: 15 }}
                        onSubmit={handleSubmit}
                    >
                        <input
                            type="text"
                            name="nombre"
                            placeholder="Nombre de la mascota"
                            value={mascota.nombre}
                            onChange={handleChange}
                            required
                        />

                        <input
                            type="text"
                            name="tipoMascota"
                            placeholder="Tipo de mascota Ej: Perro, Gato"
                            value={mascota.tipoMascota}
                            onChange={handleChange}
                            required
                        />

                        <textarea
                            name="descripcion"
                            placeholder="Descripción de la mascota"
                            value={mascota.descripcion}
                            onChange={handleChange}
                            required
                        />

                        <input
                            type="text"
                            name="ultimaVezVisto"
                            placeholder="Última vez que se vio"
                            value={mascota.ultimaVezVisto}
                            onChange={handleChange}
                            required
                        />

                        <input
                            type="text"
                            name="ubicacion"
                            placeholder="Ubicación"
                            value={mascota.ubicacion}
                            onChange={handleChange}
                            required
                        />

                        <input
                            type="text"
                            name="imagenUrl"
                            placeholder="URL de imagen"
                            value={mascota.imagenUrl}
                            onChange={handleChange}
                        />

                        <input
                            type="number"
                            name="recompensa"
                            placeholder="Recompensa"
                            value={mascota.recompensa}
                            onChange={handleChange}
                        />

                        <button type="submit" className="button">
                            Registrar mascota
                        </button>
                    </form>
                </div>
            </section>
        </>
    );
};

export default Pag_RegistroMascota;
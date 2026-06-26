import React from "react";
import {Helmet} from "react-helmet";
import Config from '../Config.json';
import useForm from "../hooks/useform";
const Title = "Registro Usuario | " + Config.SITE_TITLE;
const Domain = Config.SITE_DOMAIN;
const canonical = Config.SITE_DOMAIN + "/";

const Pag_RegistroUsuario = () => {

    const initialData = {
        nombre: "",
        email: "",
        usuario: "",
        password: ""
    };

    const {
        form ,
        loading,
        handleChange,
        handleSubmit
    } = useForm(initialData);

    return (
        <>
            <Helmet>
                <title>{Title}</title>
                <link rel="canonical" href={canonical} />
                <meta name="description" content="" />
                <meta name="keywords" content="" />
                <meta name="author" content="Lucas Salazar, Amaro Ulloa, Jose Quiero" />
            </Helmet>

            <section className="header">
                <a href="/" className="button">Volver</a>
                <div className="logo">
                    <h1 className="center">  Sanos y salvos  </h1>
                </div>
            </section>
            <br />
            <center><h2 className="logo">Registrar usuario</h2></center>
            <br />

            <section className="main">

                <div className="registro-container" style={{ maxWidth: '400px', margin: 'auto' }}>
                    <form style={{ display: 'flex', flexDirection: 'column', gap: 20 }} onSubmit={handleSubmit}>
                        <div style={{ display: 'flex', flexDirection: 'column', gap: 15 }}>

                            <div style={{ display: 'flex', alignItems: 'center', gap: 10 }}>
                                <label className="form-label" style={{ minWidth: 110 }}>Nombre:</label>
                                <input
                                    className="form-control"
                                    type="text"
                                    name="nombre"
                                    required
                                    style={{ flex: 1 }}
                                    value={form.nombre}
                                    onChange={handleChange}
                                />
                            </div>

                            <div style={{ display: 'flex', alignItems: 'center', gap: 10 }}>
                                <label className="form-label" style={{ minWidth: 110 }}>Correo electrónico:</label>
                                <input
                                    className="form-control"
                                    type="email"
                                    name="email"
                                    required
                                    style={{ flex: 1 }}
                                    value={form.email}
                                    onChange={handleChange}
                                />
                            </div>

                            <div style={{ display: 'flex', alignItems: 'center', gap: 10 }}>
                                <label className="form-label" style={{ minWidth: 110 }}>Usuario:</label>
                                <input
                                    className="form-control"
                                    type="text"
                                    name="usuario"
                                    required
                                    style={{ flex: 1 }}
                                    value={form.usuario}
                                    onChange={handleChange}
                                />
                            </div>

                            <div style={{ display: 'flex', alignItems: 'center', gap: 10 }}>
                                <label className="form-label" style={{ minWidth: 110 }}>Contraseña:</label>
                                <input
                                    className="form-control"
                                    type="password"
                                    name="password"
                                    required
                                    style={{ flex: 1 }}
                                    value={form.password}
                                    onChange={handleChange}
                                />
                            </div>

                        </div>
                        <button type="submit" className="button" disabled={loading}>Registrarse</button>
                    </form>
                    <p style={{ textAlign: 'center' }}>¿Ya tienes una cuenta? <a href="Pag_InicioSesion">Inicia sesión aquí</a></p>
                </div>
            </section>
        </>
    )
}
export default Pag_RegistroUsuario;
import React from 'react';
import { Helmet } from "react-helmet";
import Config from '../Config.json';

const Title = "Inicio Sesion | " + Config.SITE_TITLE;
const canonical = Config.SITE_DOMAIN + "/";

class Pag_inicioSesion extends React.Component {

    state = {
        usuario: "",
        password: ""
    };

    handleChange = (e) => {
        this.setState({
            [e.target.name]: e.target.value
        });
    };

    handleSubmit = async (e) => {
        e.preventDefault();

        try {

            const response = await fetch("http://localhost:8081/api/usuarios");
            const resData = await response.json();
            const usuarios = resData.data || resData;

            const usuarioEncontrado = usuarios.find(
                (u) =>
                    u.correo === this.state.usuario &&
                    u.password === this.state.password
            );

            if (usuarioEncontrado) {

                localStorage.setItem(
                    "usuario",
                    JSON.stringify(usuarioEncontrado)
                );

                alert("Inicio de sesión exitoso");

                window.location.href = "/";

            } else {
                alert("Correo o contraseña incorrectos");
            }

        } catch (error) {
            console.error(error);
            alert("Error al conectar con la API");
        }
    };

    render() {
        return (
            <>
                <Helmet>
                    <title>{Title}</title>
                    <link rel="canonical" href={canonical} />
                    <meta name="description" content="!" />
                    <meta name="keywords" content="" />
                    <meta name="author" content="Lucas Salazar, Amaro Ulloa, Jose Quiero" />
                </Helmet>

                <section className="header">
                    <a href="/" className="button">Volver</a>

                    <div className="logo">
                        <h1 className="center">Sanos y Salvos</h1>
                    </div>
                </section>

                <br />

                <center>
                    <h2 className="logo">Iniciar sesión</h2>
                </center>

                <br />

                <section className="main">

                    <div
                        className="registro-container"
                        style={{ maxWidth: '350px', margin: 'auto' }}
                    >

                        <form
                            className="form-registro"
                            style={{
                                display: 'flex',
                                flexDirection: 'column',
                                gap: 20
                            }}
                            onSubmit={this.handleSubmit}
                        >

                            <div
                                style={{
                                    display: 'flex',
                                    flexDirection: 'column',
                                    gap: 15
                                }}
                            >

                                <div
                                    style={{
                                        display: 'flex',
                                        alignItems: 'center',
                                        gap: 10
                                    }}
                                >

                                    <label
                                        htmlFor="usuario"
                                        style={{ minWidth: 110 }}
                                    >
                                        Correo:
                                    </label>

                                    <input
                                        type="email"
                                        id="usuario"
                                        name="usuario"
                                        required
                                        style={{ flex: 1 }}
                                        onChange={this.handleChange}
                                    />

                                </div>

                                <div
                                    style={{
                                        display: 'flex',
                                        alignItems: 'center',
                                        gap: 10
                                    }}
                                >

                                    <label
                                        htmlFor="password"
                                        style={{ minWidth: 110 }}
                                    >
                                        Contraseña:
                                    </label>

                                    <input
                                        type="password"
                                        id="password"
                                        name="password"
                                        required
                                        style={{ flex: 1 }}
                                        onChange={this.handleChange}
                                    />

                                </div>

                            </div>

                            <button
                                type="submit"
                                className="button"
                            >
                                Iniciar sesión
                            </button>

                        </form>

                        <p style={{ textAlign: 'center' }}>
                            ¿No tienes cuenta?
                            <a href="/Pag_RegistroUsuario">
                                {' '}Regístrate aquí
                            </a>
                        </p>

                    </div>

                </section>
            </>
        );
    }
}

export default Pag_inicioSesion;
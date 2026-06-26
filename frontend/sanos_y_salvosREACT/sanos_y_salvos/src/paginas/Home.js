import React, { useEffect, useState } from "react";
import { Helmet } from "react-helmet";
import { Swiper, SwiperSlide } from 'swiper/react';
import { Navigation } from 'swiper/modules';
import Config from '../Config.json';

import 'swiper/css';
import 'swiper/css/navigation';
import '../css/Home.css';

const Home = () => {
    const Title = "Inicio | " + Config.SITE_TITLE;
    const canonical = Config.SITE_DOMAIN + "/";

    const [mascotas, setMascotas] = useState([]);
    const [avistamientos, setAvistamientos] = useState([]);
    const [usuarioLogged, setUsuarioLogged] = useState(null);

    useEffect(() => {
        try {
            const user = JSON.parse(localStorage.getItem("usuario"));
            if (user && typeof user === 'object' && user.id) {
                setUsuarioLogged(user);
            }
        } catch (e) {
            console.error("Error al leer usuario de localStorage:", e);
            localStorage.removeItem("usuario");
        }

        fetch("http://localhost:8081/api/mascotas")
            .then((response) => response.json())
            .then((data) => setMascotas(data.data || data))
            .catch((error) => console.error("Error al obtener mascotas:", error));

        fetch("http://localhost:8081/api/avistamientos")
            .then((response) => response.json())
            .then((data) => setAvistamientos(data))
            .catch((error) => console.error("Error al obtener avistamientos:", error));
    }, []);

    const eliminarMascota = async (id) => {
        if (window.confirm("¿Estás seguro de que deseas eliminar esta mascota?")) {
            try {
                const response = await fetch(`http://localhost:8081/api/mascotas/${id}`, {
                    method: "DELETE"
                });
                if (response.ok) {
                    setMascotas(mascotas.filter(m => m.idMascota !== id));
                    alert("Mascota eliminada correctamente");
                } else {
                    alert("No se pudo eliminar la mascota");
                }
            } catch (error) {
                console.error("Error al eliminar mascota:", error);
                alert("Error al conectar con la API");
            }
        }
    };

    const eliminarAvistamiento = async (id) => {
        if (window.confirm("¿Estás seguro de que deseas eliminar este reporte?")) {
            try {
                const response = await fetch(`http://localhost:8081/api/avistamientos/${id}`, {
                    method: "DELETE"
                });
                if (response.ok) {
                    setAvistamientos(avistamientos.filter(a => a.idAvistamiento !== id));
                    alert("Reporte eliminado correctamente");
                } else {
                    alert("No se pudo eliminar el reporte");
                }
            } catch (error) {
                console.error("Error al eliminar avistamiento:", error);
                alert("Error al conectar con la API");
            }
        }
    };

    return (
        <div className="home-container">
            <Helmet>
                <title>{Title}</title>
                <link rel="canonical" href={canonical} />
            </Helmet>

            <header className="main-header">
                <div className="logo-section">
                    SANOS Y SALVOS 🦜
                </div>

                <div className="auth-links">
                    {usuarioLogged ? (
                        <>
                            <span style={{ marginRight: 15, color: '#ffde59', fontWeight: 900 }}>
                                Bienvenido, {usuarioLogged.nombre} 👤
                            </span>
                            <a href="/Pag_RegistroMascota">Registrar Mascota</a>
                            {" | "}
                            <a href="#" onClick={() => {
                                localStorage.removeItem("usuario");
                                window.location.reload();
                            }} style={{ color: '#ff4d4d' }}>
                                Cerrar Sesión
                            </a>
                        </>
                    ) : (
                        <>
                            <a href="/Pag_inicioSesion">Iniciar Sesion</a>
                            {" / "}
                            <a href="/Pag_RegistroUsuario">Registrarse</a>
                        </>
                    )}
                </div>
            </header>

            <div className="filter-bar">
                <div className="filter-item">Región <span>▼</span></div>
                <div className="filter-item">🐾 Región / Comuna 🐾</div>
            </div>

            <section className="hero-slider">
                <Swiper
                    modules={[Navigation]}
                    navigation
                    spaceBetween={20}
                    slidesPerView={2}
                    centeredSlides={true}
                    loop={true}
                >
                    <SwiperSlide>
                        <img
                            src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSpROKATor5T1wIWFMmWMw8tgwsMJvD2G1HKw&s"
                            className="slider-img"
                            alt="Plaza de San Bernardo"
                        />
                    </SwiperSlide>

                    <SwiperSlide>
                        <img
                            src="https://media.biobiochile.cl/wp-content/uploads/2016/10/municipalidad-puente-alto-e1476471492350-730x350.png"
                            className="slider-img"
                            alt="Municipalidad de Puente Alto"
                        />
                    </SwiperSlide>
                </Swiper>
            </section>

            <main className="content-section">
                <aside className="sidebar-title">
                    Post recientes
                </aside>

                <section className="feed">
                    {mascotas.length === 0 ? (
                        <p>No hay mascotas registradas.</p>
                    ) : (
                        mascotas.map((mascota) => (
                            <div className="pet-card" key={mascota.idMascota}>
                                <div className="pet-info">
                                    <h2>{mascota.nombre}</h2>

                                    <p>
                                        Tipo:<br />
                                        <strong>{mascota.tipoMascota || mascota.tipo_Mascota}</strong>
                                    </p>

                                    <p>
                                        Descripción:<br />
                                        <strong>{mascota.descripcion}</strong>
                                    </p>

                                    <p>
                                        Última vez visto:<br />
                                        <strong>{mascota.ultimaVezVisto || mascota.uvv || mascota.UVV}</strong>
                                    </p>

                                    <p>
                                        Ubicación:<br />
                                        <strong>{mascota.ubicacion || mascota.region_usuario}</strong>
                                    </p>

                                    <p>
                                        Recompensa:<br />
                                        <strong>${mascota.recompensa}</strong>
                                    </p>
                                    <div style={{ display: "flex", gap: "10px", marginTop: "10px", flexWrap: "wrap" }}>
    <button
        className="button"
        onClick={() =>
            window.location.href = `/avistamiento/${mascota.idMascota}`
        }
    >
        Reportar avistamiento
    </button>

    <button
        className="button"
        onClick={() => {
            const div = document.getElementById(`reportes-${mascota.idMascota}`);
            div.style.display = div.style.display === "none" ? "block" : "none";
        }}
    >
        Ver reportes
    </button>

    {usuarioLogged && usuarioLogged.id && mascota.usuario && mascota.usuario.id === usuarioLogged.id && (
        <button
            className="button"
            style={{ backgroundColor: "#ff4d4d", color: "#ffffff", borderColor: "#000000" }}
            onClick={() => eliminarMascota(mascota.idMascota)}
        >
            Eliminar Mascota
        </button>
    )}
</div>

<div
    id={`reportes-${mascota.idMascota}`}
    style={{ display: "none", marginTop: "15px" }}
>
    <h3>Reportes y comentarios</h3>

    {avistamientos.filter(
        (avistamiento) =>
            avistamiento.mascota?.idMascota === mascota.idMascota
    ).length === 0 ? (
        <p>No hay reportes para esta mascota.</p>
    ) : (
        avistamientos
            .filter(
                (avistamiento) =>
                    avistamiento.mascota?.idMascota === mascota.idMascota
            )
            .map((avistamiento) => (
                <div
                    key={avistamiento.idAvistamiento}
                    style={{
                        border: "3px solid #000000",
                        padding: "15px",
                        marginTop: "10px",
                        backgroundColor: "#f9f9f9"
                    }}
                >
                    <p><strong>Lugar:</strong> {avistamiento.lugar}</p>
                    <p><strong>Fecha:</strong> {avistamiento.fecha}</p>
                    <p><strong>Comentario:</strong> {avistamiento.comentario}</p>
                    <p><strong>Reportado por:</strong> {avistamiento.nombreReportante}</p>
                    <p><strong>Contacto:</strong> {avistamiento.contactoReportante}</p>
                    
                    <button
                        className="button"
                        style={{ 
                            marginTop: "10px", 
                            backgroundColor: "#ff4d4d", 
                            color: "#ffffff", 
                            borderColor: "#000000",
                            fontSize: "0.8rem",
                            padding: "6px 12px"
                        }}
                        onClick={() => eliminarAvistamiento(avistamiento.idAvistamiento)}
                    >
                        Eliminar Reporte
                    </button>
                </div>
            ))
    )}
</div>
                                    
                                </div>

                                <div className="pet-image-container">
                                    <img
                                        src={
                                            mascota.imagenUrl ||
                                            "https://cdn-icons-png.flaticon.com/512/616/616408.png"
                                        }
                                        alt={mascota.nombre}
                                        className="pet-image"
                                    />
                                    <span className="paw-icon">🐾</span>
                                </div>
                            </div>
                        ))
                    )}
                </section>
                

                <div className="right-spacer"></div>
            </main>

            <footer>
                <p>© 2026 Sanos y salvos. Lucas Salazar | Amaro Ulloa | Jose Quiero</p>
            </footer>
        </div>
    );
};

export default Home;
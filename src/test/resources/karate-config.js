function fn() {
  // 1. Definimos variables que serán globales para todos los tests
  var config = {
    baseUrl: 'http://localhost:3002/api' // La base para no repetirla en cada feature
  };

  // 2. Configuramos tiempos de espera (timeouts) para evitar que el test se quede colgado
  // si la API local no responde rápido.
  karate.configure('connectTimeout', 5000); // 5 segundos para conectar
  karate.configure('readTimeout', 5000);    // 5 segundos para recibir datos

  return config;
}
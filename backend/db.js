require("dotenv").config();

const http = require("http");
const { neon } = require("@neondatabase/serverless");
const { Pool } = require('pg')

const db = new Pool ({
        connectionString: process.env.DATABASE_URL,
        ssl:{rejectUnauthorized: false}
});

db.connect()
    .then(() => console.log("Conectado ao DB"))
    .catch(error => console.error("Não conectado"))

module.exports = db 
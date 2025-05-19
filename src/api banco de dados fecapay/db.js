// db.js
const mysql = require('mysql2');

const connection = mysql.createConnection({
  host: 'dbfpay.mysql.database.azure.com',
  user: 'adminfecapay',
  password: 'Fecapay123',
  database: 'db_fecapay',
  port: 3306,
  ssl: {
    rejectUnauthorized: false
  }
});

module.exports = connection;

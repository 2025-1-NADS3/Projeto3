// server.js
const express = require('express');
const bodyParser = require('body-parser');
const db = require('./db');

const app = express();
app.use(bodyParser.json());

// Rota de login
app.post('/login', (req, res) => {
  const { email, senha } = req.body;

  const query = 'SELECT * FROM usuarios WHERE email = ? AND senha = ?';
  db.query(query, [email, senha], (err, results) => {
    if (err) return res.status(500).json({ erro: err });
    if (results.length > 0) {
      res.json({ sucesso: true, usuario: results[0] });
    } else {
      res.status(401).json({ sucesso: false, mensagem: 'Email ou senha inválidos' });
    }
  });
});

// Rota de cadastro de novo usuário
app.post('/usuarios', (req, res) => {
  const { nome, curso, semestre, cpf, ra, data_nascimento, email, senha, saldo } = req.body;

  const query = 'INSERT INTO usuarios (nome, curso, semestre, cpf, ra, data_nascimento, email, senha, saldo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)';
  const values = [nome, curso, semestre, cpf, ra, data_nascimento, email, senha, saldo];

  db.query(query, values, (err, results) => {
    if (err) return res.status(500).json({ sucesso: false, erro: err });
    res.json({ sucesso: true, mensagem: 'Usuário cadastrado com sucesso!' });
  });
});

// Iniciar o servidor
const PORT = process.env.PORT || 3000;
app.listen(PORT, () => {
  console.log(`API rodando na porta ${PORT}`);
});

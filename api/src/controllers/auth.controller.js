const db = require("../models");
const config = require("../configs/auth.config");
const Account = db.Account;

var jwt = require("jsonwebtoken");
var bcrypt = require("bcryptjs");

exports.register = (req, res) => {
  // Save User to Database
  Account.create({
    fullname: req.body.fullname,
    username: req.body.username,
    email: req.body.email,
    password: bcrypt.hashSync(req.body.password, 8),
    roleId: req.body.roleId,
    status: true,
  })
    .then(user => {
      res.send(user);
    })
    .catch(err => {
      res.status(500).send({ message: err.message });
    });
};

exports.registerV2 = (req, res) => {
  // Save User to Database
  Account.create({
    fullname: req.body.fullname,
    username: req.body.username,
    email: req.body.email,
    password: bcrypt.hashSync(req.body.password, 8),
    roleId: req.body.roleId,
    status: true,
  })
    .then(user => {
      res.send({
        "code": "success",
        "message": "Success!",
        "account": user
      });
    })
    .catch(err => {
      res.status(500).send({ message: err.message });
    });
};

exports.save = (req, res) => {
  console.log('save', req.body);
  // Save User to Database
  Account.update({
    fullname: req.body.fullname,
    roleId: req.body.roleId,
    status: req.body.status,
  }, {
    where: {
      id: req.body.id
    }
  })
    .then(user => {
      res.send(user);
    })
    .catch(err => {
      res.status(500).send({ message: err.message });
    });
};

exports.login = (req, res) => {
  Account.findOne({
    where: {
      username: req.body.username
    },
    include: ['role']
  })
    .then(user => {
      if (!user) {
        return res.status(404).send({ message: "User Not found." });
      }

      var passwordIsValid = bcrypt.compareSync(
        req.body.password,
        user.password
      );

      if (!passwordIsValid) {
        return res.status(401).send({
          accessToken: null,
          message: "Invalid Password!"
        });
      }

      var token = jwt.sign({
        id: user.id,
        username: user.username,
        email: user.email,
        role: user.role,
      },
        config.secret, {
        expiresIn: 86400 // 24 hours
      }
      );

      res.status(200).send({
        id: user.id,
        fullname: user.fullname,
        username: user.username,
        email: user.email,
        role: "ROLE_" + user.role.name,
        accessToken: token,
      });
    })
    .catch(err => {
      res.status(500).send({ message: err.message });
    });
};
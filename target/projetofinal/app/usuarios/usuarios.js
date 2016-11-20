/**
 * Created by NatanRamos on 11/10/2016.
 */

(function () {

    function Usuarios(id, nome, data_cadastro, email, login, senha) {
        this.id = id;
        this.nome = nome;
        this.dataCadastro = data_cadastro;
        this.email = email;
        this.login = login;
        this.senha = senha;
    }

    function usuariosController() {

        var Base64={_keyStr:"ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=",encode:function(e){var t="";var n,r,i,s,o,u,a;var f=0;e=Base64._utf8_encode(e);while(f<e.length){n=e.charCodeAt(f++);r=e.charCodeAt(f++);i=e.charCodeAt(f++);s=n>>2;o=(n&3)<<4|r>>4;u=(r&15)<<2|i>>6;a=i&63;if(isNaN(r)){u=a=64}else if(isNaN(i)){a=64}t=t+this._keyStr.charAt(s)+this._keyStr.charAt(o)+this._keyStr.charAt(u)+this._keyStr.charAt(a)}return t},decode:function(e){var t="";var n,r,i;var s,o,u,a;var f=0;e=e.replace(/[^A-Za-z0-9+/=]/g,"");while(f<e.length){s=this._keyStr.indexOf(e.charAt(f++));o=this._keyStr.indexOf(e.charAt(f++));u=this._keyStr.indexOf(e.charAt(f++));a=this._keyStr.indexOf(e.charAt(f++));n=s<<2|o>>4;r=(o&15)<<4|u>>2;i=(u&3)<<6|a;t=t+String.fromCharCode(n);if(u!=64){t=t+String.fromCharCode(r)}if(a!=64){t=t+String.fromCharCode(i)}}t=Base64._utf8_decode(t);return t},_utf8_encode:function(e){e=e.replace(/rn/g,"n");var t="";for(var n=0;n<e.length;n++){var r=e.charCodeAt(n);if(r<128){t+=String.fromCharCode(r)}else if(r>127&&r<2048){t+=String.fromCharCode(r>>6|192);t+=String.fromCharCode(r&63|128)}else{t+=String.fromCharCode(r>>12|224);t+=String.fromCharCode(r>>6&63|128);t+=String.fromCharCode(r&63|128)}}return t},_utf8_decode:function(e){var t="";var n=0;var r=c1=c2=0;while(n<e.length){r=e.charCodeAt(n);if(r<128){t+=String.fromCharCode(r);n++}else if(r>191&&r<224){c2=e.charCodeAt(n+1);t+=String.fromCharCode((r&31)<<6|c2&63);n+=2}else{c2=e.charCodeAt(n+1);c3=e.charCodeAt(n+2);t+=String.fromCharCode((r&15)<<12|(c2&63)<<6|c3&63);n+=3}}return t}};
        var modeloLinhaTabela;

        function _carregar() {
            $.getJSON('../../api/usuarios', function (data) {
                _renderTable(data);
            });
        }

        _carregar();

        function _preencheForm(registro) {
            $('input[name=id]').val(registro.id);
            $('input[name=nome]').val(registro.nome);
            $('input[name=dataCadastro]').val(registro.dataCadastro);
            $('input[name=email]').val(registro.email == 'null' ? '' : registro.email);
            $('input[name=login]').val(registro.login);
            $('input[name=senha]').val(!registro.senha ? '' : Base64.decode(registro.senha));
        }

        function _adicionar() {
            window.document.getElementById('btnSalvar').disabled = true;
            _preencheForm(new Usuarios());
            $.get('../../api/utilitarios/dataAtual', function (data) {
                $('input[name=dataCadastro]').val(data);
            });
        }

        function _validaForm() {
            var salvar = window.document.getElementById('btnSalvar');
            if (window.document.getElementById('nome').value.trim() == '') {
                salvar.disabled = true;
                return;
            } else if (window.document.getElementById('login').value.trim() == '') {
                salvar.disabled = true;
                return;
            } else if (window.document.getElementById('senha').value.trim() == '') {
                salvar.disabled = true;
                return;
            }
            salvar.disabled = false;
        }

        function _salvar() {
            var id = window.document.getElementById('id').value;
            var nome = window.document.getElementById('nome').value;
            var email = window.document.getElementById('email').value;
            var login = window.document.getElementById('login').value;
            var senha = window.document.getElementById('senha').value;
            senha = Base64.encode(senha);
            var usuario = {
                id: id,
                nome: nome,
                email: email,
                login: login,
                senha: senha
            };
            $.post('../../api/usuarios', usuario , function () {
                $('#myModal').modal('hide');
                _carregar();
            });
         }

        function _remover(id) {
            $.post('../../api/usuarios/remover', {id: id} ,function () {
                _carregar();
            });
        }

        function _editar(id) {
            $.getJSON('../../api/usuarios', 'id=' + id, function (data) {
                _preencheForm(data);
                window.document.getElementById('btnSalvar').disabled = false;
            })
        }

        function _formataData(data) {
            return data.substr(8,2) + '/' + data.substr(5,2) + '/' + data.substr(0,4);
        }

        function _renderTable(data) {
            var final = '';
            modeloLinhaTabela = modeloLinhaTabela || $('table.table tbody').html();
            for (var i = 0; i < data.length; i++) {
                var res = modeloLinhaTabela;
                var linha = data[i];
                res = res.replace(/\{\{ID\}\}/g, linha.id);
                res = res.replace(/\{\{NOME\}\}/g, linha.nome);
                res = res.replace(/\{\{DATA_CADASTRO\}\}/g, _formataData(linha.dataCadastro));
                res = res.replace(/\{\{EMAIL\}\}/g, linha.email == 'null' ? '' : linha.email);
                final += res;
            }
            $('table.table tbody').html(final);
        }

        return {
            add: _adicionar,
            edit: _editar,
            save: _salvar,
            remove: _remover,
            validaForm: _validaForm
        }
    }

    $(function () {
        window.ctrl = usuariosController();
    });

})();
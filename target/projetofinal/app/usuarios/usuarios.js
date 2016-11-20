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
            $('input[name=senha]').val(registro.senha);
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
            $.post('../../api/usuarios', $('form[rule=cadastro]').serialize(), function () {
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
/**
 * Created by NatanRamos on 15/10/2016.
 */

(function () {

    function Pessoas(id, nome, tipo_pessoa, documento, data_nascimento, telefone, email, rua, numero, id_municipios, id_estados) {
        this.id = id;
        this.nome = nome;
        this.tipoPessoa = tipo_pessoa;
        this.documento = documento;
        this.dataNascimento = data_nascimento;
        this.telefone = telefone;
        this.email = email;
        this.rua = rua;
        this.numero = numero;
        this.idMunicipios = id_municipios;
        this.idEstados = id_estados;
    }

    function pessoasController() {

        var modeloLinhaTabela;

        function _carregar() {
            $('#tipoPessoa option[value=""]').attr({ selected : "selected" });
            $.getJSON('../../api/pessoas', function (data) {
                _renderTable(data);
            });
            $.getJSON('../../api/municipios', function (data) {
                _renderMunicipios(data);
                $('#idMunicipios option[value=""]').attr({ selected : "selected" });
            });
            $.getJSON('../../api/estados', function (data) {
                _renderEstados(data);
                $('#idEstados option[value=""]').attr({ selected : "selected" });
            });
        }

        _carregar();

        function _preencheForm(registro) {
            $('input[name=id]').val(registro.id);
            $('input[name=nome]').val(registro.nome);
            $('select[name=tipoPessoa]').val(registro.tipoPessoa);
            $('input[name=documento]').val(registro.documento);
            $('input[name=dataNascimento]').val(registro.dataNascimento);
            $('input[name=telefone]').val(registro.telefone);
            $('input[name=email]').val(registro.email == 'null' ? '' : registro.email);
            $('input[name=rua]').val(registro.rua == 'null' ? '' : registro.rua);
            $('input[name=numero]').val(registro.numero == 'null' ? '' : registro.numero);
            $('select[name=idMunicipios]').val(registro.idMunicipios);
            $('select[name=idEstados]').val(registro.idEstados);
        }

        function _adicionar() {
            _preencheForm(new Pessoas());
        }

        function _validaForm() {
            if (window.document.getElementById('nome').value.trim() == '') {
                alert('Informe o nome!');
                window.document.getElementById('nome').focus();
                return false;
            } else if (window.document.getElementById('tipoPessoa').value.trim() == '') {
                alert('Informe o tipo!');
                window.document.getElementById('tipoPessoa').focus();
                return false;
            } else if (window.document.getElementById('documento').value.trim() == '') {
                alert('Informe o CPF/CNPJ!');
                window.document.getElementById('documento').focus();
                return false;
            } else if (window.document.getElementById('telefone').value.trim() == '') {
                alert('Informe o telefone!');
                window.document.getElementById('telefone').focus();
                return false;
            }
            return true;
        }

        function _salvar() {
            if (_validaForm()) {
                $.post('../../api/pessoas', $('form[rule=cadastro]').serialize(), function () {
                    $('#myModal').modal('hide');
                    _carregar();
                });
            }
        }

        function _remover(id) {
            $.post('../../api/pessoas/remover', {id: id} ,function () {
                _carregar();
            });
        }

        function _editar(id) {
            $.getJSON('../../api/pessoas', 'id=' + id, function (data) {
                _changeCampos(data.tipoPessoa);
                _preencheForm(data);
            })
        }

        function _formataData(data) {
            if (data == 'null') {
                return '';
            }
            return data.substr(8,2) + '/' + data.substr(5,2) + '/' + data.substr(0,4);
        }

        function _formataDocumento(tipoPessoa, documento) {
            if (tipoPessoa == 'F') {
                return documento.substr(0,3) + '.' + documento.substr(3,3) + '.' + documento.substr(6,3) + '-' + documento.substr(9,2);
            } else if (tipoPessoa == 'J') {
                return documento.substr(0,2) + '.' + documento.substr(2,3) + '.' + documento.substr(5,3) + '/' + documento.substr(8,4) + '-' + documento.substr(12,2);
            }
            return documento;
        }

        function _renderTable(data) {
            var final = '';
            modeloLinhaTabela = modeloLinhaTabela || $('table.table tbody').html();
            for (var i = 0; i < data.length; i++) {
                var res = modeloLinhaTabela;
                var linha = data[i];
                res = res.replace(/\{\{ID\}\}/g, linha.id);
                res = res.replace(/\{\{NOME\}\}/g, linha.nome);
                res = res.replace(/\{\{TIPO_PESSOA\}\}/g, linha.tipoPessoa == 'J' ? 'Jurídica': 'Física');
                res = res.replace(/\{\{DOCUMENTO\}\}/g, _formataDocumento(linha.tipoPessoa, linha.documento));
                res = res.replace(/\{\{DATA_NASCIMENTO\}\}/g, _formataData(linha.dataNascimento));
                res = res.replace(/\{\{TELEFONE\}\}/g, linha.telefone);
                res = res.replace(/\{\{EMAIL\}\}/g, linha.email == 'null' ? '' : linha.email);
                final += res;
            }
            $('table.table tbody').html(final);
        }

        function _renderMunicipios(data) {
            var options = '<option value=""></option>';
            data.forEach(function (municipio) {
                options += '<option value="' + municipio.id + '">' + municipio.nome + '</option>';
            });
            $("#idMunicipios").html(options);
        }

        function _renderEstados(data) {
            var options = '<option value=""></option>';
            data.forEach(function (estado) {
                options += '<option value="' + estado.id + '">' + estado.nome + '</option>';
            });
            $("#idEstados").html(options);
        }

        function _changeCampos(tipoPessoa) {
            //var tipoPessoa = window.document.getElementById('tipoPessoa').value;
            var dataNascimento = window.document.getElementById('dataNascimento');
            var documento = window.document.getElementById('documento');
            if (tipoPessoa == '') {
                dataNascimento.value = '';
                documento.value = '';
                dataNascimento.disabled = true;
                documento.disabled = true;
                return;
            }
            if (tipoPessoa == 'F') {
                dataNascimento.disabled = false;
                documento.disabled = false;
                var im = new Inputmask("999.999.999-99");
                im.mask(documento);
                return;
            }
            if (tipoPessoa == 'J') {
                dataNascimento.disabled = true;
                documento.disabled = false;
                var im = new Inputmask("99.999.999/9999-99");
                im.mask(documento);
                return;
            }
        }

        function _changeTipoPessoa() {
            var tipoPessoa = window.document.getElementById('tipoPessoa').value;
            var dataNascimento = window.document.getElementById('dataNascimento');
            var documento = window.document.getElementById('documento');
            if (tipoPessoa == '') {
                dataNascimento.value = '';
                documento.value = '';
                dataNascimento.disabled = true;
                documento.disabled = true;
                return;
            }
            if (tipoPessoa == 'F') {
                dataNascimento.disabled = false;
                documento.disabled = false;
                dataNascimento.value = '';
                documento.value = '';
                var im = new Inputmask("999.999.999-99");
                im.mask(documento);
                return;
            }
            if (tipoPessoa == 'J') {
                dataNascimento.disabled = true;
                documento.disabled = false;
                dataNascimento.value = '';
                documento.value = '';
                var im = new Inputmask("99.999.999/9999-99");
                im.mask(documento);
                return;
            }
        }

        return {
            add: _adicionar,
            edit: _editar,
            save: _salvar,
            remove: _remover,
            changeTipoPessoa: _changeTipoPessoa
        }
    }

    $(function () {
        window.ctrl = pessoasController();
    });

})();
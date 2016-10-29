/**
 * Created by NatanRamos on 21/10/2016.
 */

(function () {

    function Controles(id, mensalista, id_pessoas, placa, marca, modelo, cor, responsavel, data_hora_entrada, data_hora_saida, situacao, valor) {
        this.id = id;
        this.mensalista = mensalista;
        this.idPessoas = id_pessoas;
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.cor = cor;
        this.responsavel = responsavel;
        this.dataHoraEntrada = data_hora_entrada;
        this.dataHoraSaida = data_hora_saida;
        this.situacao = situacao;
        this.valor = valor;
    }

    function controlesController() {

        var modeloLinhaTabela;

        function _carregar() {
            $.getJSON('../../api/controles', function (data) {
                _renderTable(data);
            });
            $.getJSON('../../api/pessoas', function (data) {
                _renderPessoas(data);
                $('#idPessoas option[value=""]').attr({ selected : "selected" });
            });
        }

        _carregar();

        function _preencheForm(registro) {
            $('input[name=id]').val(registro.id);
            $('select[name=mensalista]').val(registro.mensalista);
            $('select[name=idPessoas]').val(registro.idPessoas);
            $('input[name=placa]').val(registro.placa);
            $('select[name=marca]').val(registro.marca);
            $('input[name=modelo]').val(registro.modelo == 'null' ? '' : registro.modelo);
            $('input[name=cor]').val(registro.cor == 'null' ? '' : registro.cor);
            $('input[name=responsavel]').val(registro.responsavel == 'null' ? '' : registro.responsavel);
            $('input[name=dataHoraEntrada]').val(registro.dataHoraEntrada);
            $('input[name=dataHoraSaida]').val(registro.dataHoraSaida);
            $('select[name=situacao]').val(registro.situacao);
            $('input[name=valor]').val(registro.valor);
        }

        function _adicionar() {
            _preencheForm(new Controles());
            $('select[name=situacao]').val('A');
            _changeCampos(false);
        }

        function _validaForm() {
            if (window.document.getElementById('mensalista').value.trim() == '') {
                alert('Informe se a pessoa é um mensalista!');
                window.document.getElementById('mensalista').focus();
                return false;
            } else {
                if (window.document.getElementById('mensalista').value.trim() == 'S') {
                    if (window.document.getElementById('idPessoas').value.trim() == '') {
                        alert('Informe a pessoa!');
                        window.document.getElementById('idPessoas').focus();
                        return false;
                    }
                } else {
                    if (window.document.getElementById('responsavel').value.trim() == '') {
                        alert('Informe o responsável!');
                        window.document.getElementById('responsavel').focus();
                        return false;
                    }
                }
            }
            if (window.document.getElementById('placa').value.trim() == '') {
                alert('Informe a placa!');
                window.document.getElementById('placa').focus();
                return false;
            } else if (window.document.getElementById('valor').value.trim() == '') {
                alert('Informe o valor!');
                window.document.getElementById('valor').focus();
                return false;
            }
            return true;
        }

        function _salvar() {
            if (_validaForm()) {
                $.post('../../api/controles', $('form[rule=cadastro]').serialize(), function () {
                    $('#myModal').modal('hide');
                    _carregar();
                });
            }
        }

        function _remover(id) {
            $.post('../../api/controles/remover', {id: id} ,function () {
                _carregar();
            });
        }

        function _editar(id) {
            $.getJSON('../../api/controles', 'id=' + id, function (data) {
                _preencheForm(data);
                _changeCampos(true);
            })
        }

        function _encerrar(id, situacao) {
            $.post('../../api/controles/encerrar', {id: id, situacao: situacao} ,function () {
                _carregar();
            });
        }

        function _pendente(id) {
            $.post('../../api/controles/pendente', {id: id} ,function () {
                _carregar();
            });
        }

        function _formataSituacao(situacao) {
            if (situacao == 'A') {
                return 'Aberto';
            } else if (situacao == 'P') {
                return 'Pendente';
            } else if (situacao == 'E') {
                return 'Encerrado';
            }
            return '';
        }

        function _formataPlaca(placa) {
            return placa.substr(0,3) + '-' + placa.substr(3,4);
        }

        function _definirAcoes(id, mensalista, situacao) {
            var retorno = '<a style="padding: 3px;" title="Editar" href="#" onclick="ctrl.edit('+id+')" data-toggle="modal" data-target="#myModal"><span class="glyphicon glyphicon-pencil"></span></a>'
            if (situacao == 'A') {
                retorno += '<a style="padding: 3px;" title="Excluir" href="#" onclick="ctrl.remove('+id+')"><span class="glyphicon glyphicon-trash"></span></a>';
                /*retorno += '<a style="padding: 3px;" title="Encerrar" href="#" onclick="ctrl.encerrar('+id+','+situacao+')"><span class="glyphicon glyphicon-stop"></span></a>';*/
                retorno += "<a style='padding: 3px;' title='Encerrar' href='#' onclick='ctrl.encerrar("+id+","+situacao+")'><span class='glyphicon glyphicon-stop'></span></a>";
            }
            if (mensalista == 'N' && situacao == 'A') {
                retorno += '<a style="padding: 3px;" title="Tornar pendente" href="#" onclick="ctrl.pendente('+id+')"><span class="glyphicon glyphicon-ban-circle"></span></a>';
            }
            if (mensalista == 'N' && situacao == 'P') {
                retorno += '<a style="padding: 3px;" title="Encerrar" href="#" onclick="ctrl.encerrar('+id+','+situacao+')"><span class="glyphicon glyphicon-stop"></span></a>';
            }
            return retorno;
        }

        function _renderTable(data) {
            var final = '';
            modeloLinhaTabela = modeloLinhaTabela || $('table.table tbody').html();
            for (var i = 0; i < data.length; i++) {
                var res = modeloLinhaTabela;
                var linha = data[i];
                res = res.replace(/\{\{ID\}\}/g, linha.id);
                res = res.replace(/\{\{MENSALISTA\}\}/g, linha.mensalista == 'S' ? 'Sim': 'Não');
                res = res.replace(/\{\{PESSOA\}\}/g, !linha.pessoa.nome ? '' : linha.pessoa.nome);
                res = res.replace(/\{\{PLACA\}\}/g, _formataPlaca(linha.placa));
                res = res.replace(/\{\{MODELO\}\}/g, linha.modelo == 'null' ? '' : linha.modelo);
                res = res.replace(/\{\{DATA_HORA_ENTRADA\}\}/g, linha.dataHoraEntrada);
                res = res.replace(/\{\{DATA_HORA_SAIDA\}\}/g, linha.dataHoraSaida);
                res = res.replace(/\{\{RESPONSAVEL\}\}/g, linha.responsavel == 'null' ? '' : linha.responsavel);
                res = res.replace(/\{\{VALOR\}\}/g, linha.valor);
                res = res.replace(/\{\{SITUACAO\}\}/g, _formataSituacao(linha.situacao));
                res = res.replace(/\{\{ACOES\}\}/g, _definirAcoes(linha.id, linha.mensalista, linha.situacao));
                final += res;
            }
            $('table.table tbody').html(final);
        }

        function _renderPessoas(data) {
            var options = '<option value=""></option>';
            data.forEach(function (pessoa) {
                options += '<option value="' + pessoa.id + '">' + pessoa.nome + '</option>';
            });
            $("#idPessoas").html(options);
        }

        function _changeCampos(habilita) {
            var pessoa = window.document.getElementById('idPessoas');
            var mensalista = window.document.getElementById('mensalista');
            var placa = window.document.getElementById('placa');
            var valor = window.document.getElementById('valor');
            var marca = window.document.getElementById('marca');
            var modelo = window.document.getElementById('modelo');
            var cor = window.document.getElementById('cor');
            var responsavel = window.document.getElementById('responsavel');
            var salvar = window.document.getElementById('btnSalvar');
            pessoa.disabled = habilita;
            mensalista.disabled = habilita;
            placa.disabled = habilita;
            valor.disabled = habilita;
            marca.disabled = habilita;
            modelo.disabled = habilita;
            cor.disabled = habilita;
            responsavel.disabled = habilita;
            salvar.disabled = habilita;
        }

        return {
            add: _adicionar,
            edit: _editar,
            save: _salvar,
            remove: _remover,
            encerrar: _encerrar,
            pendente: _pendente
        }
    }

    $(function () {
        window.ctrl = controlesController();
    });

})();
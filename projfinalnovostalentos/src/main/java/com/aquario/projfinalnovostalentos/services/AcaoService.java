package com.aquario.projfinalnovostalentos.services;

import com.aquario.projfinalnovostalentos.models.Acao;
import com.aquario.projfinalnovostalentos.models.Aquario;
import com.aquario.projfinalnovostalentos.models.Equipamento;
import com.aquario.projfinalnovostalentos.models.HistoricoEquipamento;
import com.aquario.projfinalnovostalentos.models.Parametro;
import com.aquario.projfinalnovostalentos.models.ParametroAquario;
import com.aquario.projfinalnovostalentos.models.ParametroEspecie;
import com.aquario.projfinalnovostalentos.models.Usuario;
import com.aquario.projfinalnovostalentos.repositories.AcaoRepository;
import com.aquario.projfinalnovostalentos.repositories.AquarioRepository;
import com.aquario.projfinalnovostalentos.repositories.EquipamentoRepository;
import com.aquario.projfinalnovostalentos.repositories.EspecieRepository;
import com.aquario.projfinalnovostalentos.repositories.HistoricoEquipamentoRepository;
import com.aquario.projfinalnovostalentos.repositories.ParametroAquarioRepository;
import com.aquario.projfinalnovostalentos.repositories.ParametroEspecieRepository;
import com.aquario.projfinalnovostalentos.repositories.ParametroRepository;
import com.aquario.projfinalnovostalentos.utils.FileUpload;
import com.aquario.projfinalnovostalentos.utils.LoginForm;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class AcaoService {
    
    private AcaoRepository repository;

    @Autowired
    private ParametroService parametroService;

    @Autowired
    private AquarioRepository aquarioRepository;

    @Autowired
    private EquipamentoRepository equipamentoRepository;

    @Autowired
    private ParametroAquarioRepository parametroAquarioRepository;

    @Autowired
    private HistoricoEquipamentoRepository historicoEquipamentoRepository;
    
    public AcaoService(AcaoRepository repository){
        this.repository = repository;
    }

    public void gerarAcoes()
    {
        ArrayList<Acao> acoes = new ArrayList<>();

        Iterable<Aquario> aquarios = aquarioRepository.findAll();
        for (Aquario aquario : aquarios) {
            Iterable<ParametroAquario> parametros = parametroAquarioRepository.findByAquario(aquario);
            for (ParametroAquario parametroAquario : parametros) {
                parametroService.calcularClasse(parametroAquario);
                String classe = parametroAquario.getClasse();
                if(classe.equals("muito-ruim"))
                {
                    acoes.add(criarAcao(aquario, parametroAquario.getParametro(), "Ajustar imediatamente"));
                }
                else if(classe.equals("ruim"))
                {
                    acoes.add(criarAcao(aquario, parametroAquario.getParametro(), "Ajustar"));
                }
                else if(classe.equals("bom"))
                {
                    acoes.add(criarAcao(aquario, parametroAquario.getParametro(), "Ajustar levemente"));
                }
            }
        }

        Iterable<Equipamento> equipamentos = equipamentoRepository.findAll();
        for (Equipamento equipamento : equipamentos) {
            Optional<HistoricoEquipamento> historicoBusca = historicoEquipamentoRepository.findFirstByEquipamentoOrderByPkDesc(equipamento);
            if(historicoBusca.isPresent())
            {
                HistoricoEquipamento historico = historicoBusca.get();
                Date data = historico.getData();
                Time hora = historico.getHora();
                
                LocalDate date = data.toLocalDate();
                LocalTime time = hora.toLocalTime();
            
                LocalDateTime dateTime = date.atTime(time);
                LocalDateTime now = LocalDateTime.now();
                
                Duration duration = Duration.between(dateTime, now);
                long hours = duration.toHours();
                if(hours > 8)
                {
                    if(equipamento.isStatus())
                        acoes.add(criarAcao(equipamento, "Desligar"));
                    else
                        acoes.add(criarAcao(equipamento, "Ligar"));
                }
            }
            else
            {
                if(equipamento.isStatus())
                    acoes.add(criarAcao(equipamento, "Desligar"));
                else
                    acoes.add(criarAcao(equipamento, "Ligar"));
            }
        }

        repository.saveAll(acoes);
    }

    private Acao criarAcao(Aquario aquario, Parametro parametro, String sugestao)
    {
        long millis = System.currentTimeMillis();

        Acao acao = new Acao();
        acao.setAquario(aquario);
        acao.setParametro(parametro);
        acao.setSugestao(sugestao);
        acao.setData(new Date(millis));
        acao.setHora(new Time(millis));
        return acao;
    }

    private Acao criarAcao(Equipamento equipamento, String sugestao)
    {
        long millis = System.currentTimeMillis();

        Acao acao = new Acao();
        acao.setAquario(equipamento.getAquario());
        acao.setEquipamento(equipamento);
        acao.setSugestao(sugestao);
        acao.setData(new Date(millis));
        acao.setHora(new Time(millis));
        return acao;
    }
}

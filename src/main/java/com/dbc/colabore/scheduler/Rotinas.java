package com.dbc.colabore.scheduler;

import com.dbc.colabore.service.CampanhaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@RequiredArgsConstructor
@Component
@Slf4j
public class Rotinas {
    private static final SimpleDateFormat dateFormat
            = new SimpleDateFormat("HH:mm:ss");

    private final CampanhaService campanhaService;

    @Scheduled(fixedDelay = 50000)
    public void atualizaStatusDaCampanhaComMetaAtingida() {
        campanhaService.alteraStatusDaCampanhaQuandoMetaAtingida();
        log.info("{}", dateFormat.format(new Date()));
    }

    @Scheduled(cron = "59 59 23 * * *", zone = "GMT-3")
    public void atualizaStatusDaCampanhaQuandoAtingeADataLimiteDeContribuicao() {
        campanhaService.alteraStatusDaCampanhaQuandoAtingeDataDeEncerramento();
        log.info("{}", dateFormat.format(new Date()));
    }
}

package com.globalshop.order.notification;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Cliente de email legado con interfaz incompatible.
 * Representa un sistema externo que no podemos modificar.
 */
public class LegacyEmailClient {

    private static final Logger logger = LoggerFactory.getLogger(LegacyEmailClient.class);

    public void sendEmail(String recipient, String subject, String body) {
        logger.info("[LegacyEmailClient] TO: {} | SUBJECT: {} | BODY: {}", recipient, subject, body);
    }

    public void sendErrorAlert(String recipient, String errorCode, String details) {
        logger.warn("[LegacyEmailClient] ERROR ALERT TO: {} | CODE: {} | DETAILS: {}", recipient, errorCode, details);
    }

    public boolean ping() {
        return true;
    }
}

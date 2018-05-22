package by.kamotskaya.internet_provider.receiver;

import by.kamotskaya.internet_provider.command.CommandResult;
import by.kamotskaya.internet_provider.constant.PagePath;
import by.kamotskaya.internet_provider.controller.RequestContent;
import by.kamotskaya.internet_provider.dao.impl.TariffDAO;
import by.kamotskaya.internet_provider.entity.Tariff;
import by.kamotskaya.internet_provider.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

/**
 * @author Lena Kamotskaya
 */
public class TariffReceiver {

    private static final Logger LOGGER = LogManager.getLogger(TariffReceiver.class);

    private static TariffDAO tariffDAO = new TariffDAO();

    public TariffReceiver() {}
/*
    public static CommandResult addNewTariff(RequestContent content) {

        String tName = content.getRequestParameters().get("t_name")[0];
        String connectionPayment = content.getRequestParameters().get("connection_payment")[0];
        String dailyFee = content.getRequestParameters().get("daily_fee")[0];
        String trafficLimit = content.getRequestParameters().get("traffic_limit")[0];
        String speedIn = content.getRequestParameters().get("speed_in")[0];
        String speedOut = content.getRequestParameters().get("speed_out")[0];
        String overrunFee = content.getRequestParameters().get("overrun_fee")[0];

        Tariff tariff = new Tariff();
        tariff.settName(tName);
        tariff.setConnectionPayment(Double.parseDouble(connectionPayment));
        tariff.setDailyFee(Double.parseDouble(dailyFee));
        tariff.setTrafficLimit(Integer.parseInt(trafficLimit));
        tariff.setSpeedIn(speedIn);
        tariff.setSpeedOut(speedOut);
        tariff.setOverrunFee(Double.parseDouble(overrunFee));
        try {
            Optional tId = tariffDAO.findIdByName(tName);
            if (tId.isPresent()) {//если такой тариф уже есть
                tariffDAO.update(tariff);
            } else {
                tariffDAO.add(tariff);
            }
        } catch (DAOException e) {
            content.putRequestAttribute("errorMessage", "Error is occurred while adding a new tariff.");
            return new CommandResult(CommandResult.ResponseType.FORWARD, PagePath.ERROR);
        }
        return new CommandResult(CommandResult.ResponseType.FORWARD, PagePath.TARIFFS);
    }

    public static CommandResult addSale(RequestContent content) {
        String tName = content.getRequestParameters().get("t_name")[0];

        try {
            int tId = (int) tariffDAO.findIdByName(tName).get();
        } catch (DAOException e) {
            content.putRequestAttribute("errorMessage", "Error is occurred while finding a tariff.");
            return new CommandResult(CommandResult.ResponseType.FORWARD, PagePath.ERROR);
        }
        return new CommandResult(CommandResult.ResponseType.FORWARD, PagePath.TARIFFS);
    }
    */
}


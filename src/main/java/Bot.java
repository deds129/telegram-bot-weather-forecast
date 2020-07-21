import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/*
!!!!!!!НУЖЕН VPN!!!!!!
 */

public class Bot extends TelegramLongPollingBot {





    public static void main(String[] args) {
        // инициализируем бота
        ApiContextInitializer.init();

        //создаем объект

        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();//создаем объект

        //регистрируем бота
        try {
            telegramBotsApi.registerBot(new Bot());
             //случай исключения
        }catch (TelegramApiRequestException e){
            e.printStackTrace();
        }

    }
    //что будет отвечать бот?
    public void sendMsg(Message message,String text){
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        // кому отвечать?
        sendMessage.setChatId(message.getChatId().toString());//в какой чат отправить ответ
        sendMessage.setReplyToMessageId(message.getMessageId());//на какое сообщение нужно ответить
        sendMessage.setText(text);

        try{
            setButtons(sendMessage); // добавляем кнопки
            sendMessage(sendMessage);// добавляем ответ на сообщения


        }catch (TelegramApiException e){
            e.printStackTrace();
        }
    }

    //переопределили методы

    // метод для приема сообщений
    //очередь ожидающих запрсов обновление
    public void onUpdateReceived(Update update) {
        Model model = new Model();
        Message message=update.getMessage();
        if(message!=null && message.hasText()){
            switch (message.getText()){
                case "/help":
                    sendMsg(message,"Чем я мог тебе помочь?");
                    break;
                case "/setting":
                    sendMsg(message,"Что будем настраивать?");
                            break;
                            default:
                                try{
                                    sendMsg(message,Weather.getWeather(message.getText(),model));
                                }catch (IOException e){
                                   // e.printStackTrace();
                                    sendMsg(message,"Такой город не найден");
                                }

            }
        }

    }
// создаем кнопочки
public void  setButtons(SendMessage sendMessage){
    ReplyKeyboardMarkup replayKeyboardMarkup = new ReplyKeyboardMarkup();
    sendMessage.setReplyMarkup(replayKeyboardMarkup);
    replayKeyboardMarkup.setSelective(true);
    replayKeyboardMarkup.setResizeKeyboard(true);
    replayKeyboardMarkup.setOneTimeKeyboard(false);

    List<KeyboardRow> keyboardRowList = new ArrayList<>();
    KeyboardRow keyboardFirstRow = new KeyboardRow();

    keyboardFirstRow.add(new KeyboardButton("/help"));
    keyboardFirstRow.add(new KeyboardButton("/setting"));

    keyboardRowList.add(keyboardFirstRow);
    replayKeyboardMarkup.setKeyboard(keyboardRowList);

}




//метод для возврата имени бота указанного при региср
    public String getBotUsername() {
        return "weratherPogoda_bot";
    }
//токен который мы получили от БАТИ
    public String getBotToken() {
        return "847678552:AAHh04aSZZHqRVHLN7Bhii4XVS9P6k-Q0is";
    }
}

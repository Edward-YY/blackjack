package com.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 手札のコレクションクラスです。
 */
public class Hands {

    public static final int MAX_TOTAL_VALUE = 21;

    private final List<Card> cardList;

    public Hands() {
        cardList = new ArrayList<>();
    }

    /**
     * カードを手札に加えます。
     * @param card 加えるカード
     */
    public void addHand(Card card) {
        cardList.add(card);
    }

    public List<Card> getCardList() {
        return Collections.unmodifiableList(cardList);
    }

    /**
     * 手札の合計値を計算して返します。
     * @return 合計値
     */
    public int calculateTotal() {
        int total = 0;
        int aceCount = 0;

        for (Card card : cardList) {
            if (isAce(card)) {
                aceCount++;
            }
            total += card.getNumber().getBlackJackValue();
        }

        // エースは11として計算しているので、
        // 最大でエースの枚数だけ10を引ける
        for (int i = 0; i < aceCount; i++) {
            if (total > MAX_TOTAL_VALUE) {
                total -= 10;
            }
        }

        return total;
    }

    private boolean isAce(Card card) {
        return card.getNumber() == Number.ACE;
    }

}
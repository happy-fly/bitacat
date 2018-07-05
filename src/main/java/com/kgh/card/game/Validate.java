package com.kgh.card.game;

import java.util.List;

import com.kgh.card.bean.Card;

public interface Validate {

	boolean validate(List<Card> current, List<Card> previous);
}

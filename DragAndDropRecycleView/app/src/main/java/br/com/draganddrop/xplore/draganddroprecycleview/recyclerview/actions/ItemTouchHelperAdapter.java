package br.com.draganddrop.xplore.draganddroprecycleview.recyclerview.actions;

/**
 * Created by r028367 on 08/12/2017.
 */

public interface ItemTouchHelperAdapter {
    /**
     * Metodo que sera executado quando o usuario mover o item de lugar.
     * */
    void onItemMove(int newPosition, int lastPosition);

    /**
     * Metodo que sera executado quando o usuario jogar o item para o lado com a intencao
     * de faze-lo desaparecer.
     * */
    void onItemSwiped(int position);
}

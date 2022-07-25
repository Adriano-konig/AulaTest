package br.com.zup.listacompras.detalhe

import android.view.View
import android.widget.EditText
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import br.com.zup.listacompras.ERROR_DESCRICAO
import br.com.zup.listacompras.ERROR_NOME
import br.com.zup.listacompras.R
import br.com.zup.listacompras.produto.ProdutoFragment
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
 class DetalheFragmentTest {

        @Test
        fun showSnackbarError_whenNomeIsEmpty(){
            val scenario = launchFragmentInContainer<ProdutoFragment>()
            onView(withId(R.id.bvAdicionar)).perform(click())
                onView(hasErrorText(ERROR_NOME))
                    .check(matches(withId(R.id.etNomeProduto)))
            onView(hasErrorText(ERROR_DESCRICAO))
                .check(matches(withId(R.id.etDetalheProduto)))
    }

    @Test
    fun detalheShowErrorAndNomeDoesnt_whenOnlyDetalheIsEmpty(){
        val scenario = launchFragmentInContainer<ProdutoFragment>()
        onView(withId(R.id.etNomeProduto)).perform(typeText("asdasdasd"))
        onView(withId(R.id.bvAdicionar)).perform(click())
        onView(hasErrorText(ERROR_DESCRICAO))
            .check(matches(withId(R.id.etDetalheProduto)))
        onView(withId(R.id.etNomeProduto)).check(matches(hasNoErrorText()))

    }

    fun hasNoErrorText(): BoundedMatcher<View?, EditText> {
        return object : BoundedMatcher<View?, EditText>(EditText::class.java) {

            override fun matchesSafely(view: EditText): Boolean {
                return view.error == null
            }

            override fun describeTo(description: org.hamcrest.Description?) {
                description?.appendText("has no error text: ");
            }
        }
    }
}
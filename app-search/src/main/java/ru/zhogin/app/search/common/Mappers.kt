package ru.zhogin.app.search.common

import ru.zhogin.app.search.data.database.models.offer.ButtonDbo
import ru.zhogin.app.search.data.database.models.offer.OfferDbo
import ru.zhogin.app.search.data.database.models.vacancy.AddressDbo
import ru.zhogin.app.search.data.database.models.vacancy.ExperienceDbo
import ru.zhogin.app.search.data.database.models.vacancy.SalaryDbo
import ru.zhogin.app.search.data.database.models.vacancy.VacancyDbo
import ru.zhogin.app.search.data.database.utils.GsonConverter.toJson
import ru.zhogin.app.search.data.database.utils.GsonConverter.toType
import ru.zhogin.app.search.data.network.models.offer.ButtonDto
import ru.zhogin.app.search.data.network.models.offer.OfferDto
import ru.zhogin.app.search.data.network.models.vacancy.AddressDto
import ru.zhogin.app.search.data.network.models.vacancy.ExperienceDto
import ru.zhogin.app.search.data.network.models.vacancy.SalaryDto
import ru.zhogin.app.search.data.network.models.vacancy.VacancyDto
import ru.zhogin.app.search.domain.models.offer.Button
import ru.zhogin.app.search.domain.models.offer.Offer
import ru.zhogin.app.search.domain.models.vacancy.Address
import ru.zhogin.app.search.domain.models.vacancy.Experience
import ru.zhogin.app.search.domain.models.vacancy.Salary
import ru.zhogin.app.search.domain.models.vacancy.Vacancy

internal fun VacancyDto.toVacancy() : Vacancy {
    return Vacancy(
        address = address.toAddress(),
        appliedNumber = appliedNumber,
        company = company,
        description = description,
        experience = experience.toExperience(),
        id = id,
        isFavorite = isFavorite,
        lookingNumber = lookingNumber,
        publishedDate = publishedDate,
        questions = questions,
        responsibilities = responsibilities,
        salary = salary.toSalary(),
        schedules = schedules,
        title = title,
    )
}

private fun SalaryDto.toSalary() : Salary {
    return Salary(
        full = full,
        short = short,
    )
}

private fun ExperienceDto.toExperience() : Experience {
    return Experience(
        previewText = previewText,
        text = text,
    )
}

private fun AddressDto.toAddress() : Address {
    return Address(
        house = house,
        street = street,
        town = town,
    )
}

internal fun OfferDto.toOffer() : Offer {
    return Offer(
        button = button?.toButton(),
        id = id,
        link = link,
        title = title,
    )
}
private fun ButtonDto.toButton() : Button {
    return Button(
        text = text
    )
}

internal fun OfferDbo.toOffer() : Offer {
    return Offer(
        button = button?.toButton(),
        id = id,
        link = link,
        title = title,
    )
}
private fun ButtonDbo.toButton() : Button {
    return Button(
        text = text
    )
}

fun VacancyDbo.toVacancy() : Vacancy {
    return Vacancy(
        address = address.toAddress(),
        appliedNumber = appliedNumber,
        company = company,
        description = description,
        experience = experience.toExperience(),
        id = id,
        isFavorite = isFavorite,
        lookingNumber = lookingNumber,
        publishedDate = publishedDate,
        questions = questions.toType(),
        responsibilities = responsibilities,
        salary = salary.toSalary(),
        schedules = schedules.toType(),
        title = title,
    )
}

private fun AddressDbo.toAddress() : Address {
    return Address(
        house = house,
        street = street,
        town = town,
    )
}
private fun SalaryDbo.toSalary() : Salary {
    return Salary(
        full = full,
        short = short,
    )
}

private fun ExperienceDbo.toExperience() : Experience {
    return Experience(
        previewText = previewText,
        text = text,
    )
}


internal fun VacancyDto.toVacancyDbo() : VacancyDbo {
    return VacancyDbo(
        address = address.toAddressDbo(),
        appliedNumber = appliedNumber,
        company = company,
        description = description,
        experience = experience.toExperienceDbo(),
        id = id,
        isFavorite = isFavorite,
        lookingNumber = lookingNumber,
        publishedDate = publishedDate,
        questions = questions.toJson(),
        responsibilities = responsibilities,
        salary = salary.toSalaryDbo(),
        schedules = schedules.toJson(),
        title = title,
    )
}

private fun SalaryDto.toSalaryDbo() : SalaryDbo {
    return SalaryDbo(
        full = full,
        short = short,
    )
}

private fun ExperienceDto.toExperienceDbo() : ExperienceDbo {
    return ExperienceDbo(
        previewText = previewText,
        text = text,
    )
}

private fun AddressDto.toAddressDbo() : AddressDbo {
    return AddressDbo(
        house = house,
        street = street,
        town = town,
    )
}

internal fun OfferDto.toOfferDbo() : OfferDbo {
    return OfferDbo(
        button = button?.toButtonDbo(),
        id = id,
        link = link,
        title = title,
    )
}
private fun ButtonDto.toButtonDbo() : ButtonDbo {
    return ButtonDbo(
        text = text
    )
}